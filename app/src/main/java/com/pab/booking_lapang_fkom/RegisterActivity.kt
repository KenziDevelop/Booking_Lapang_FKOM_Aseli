package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.RegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: RegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ubah teks tombol jadi "Daftar" (karena di XML tertulis "Masuk")
        binding.btnRegister.text = "Daftar"

        // Tombol Kembali
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Link "Sudah punya akun? Masuk"
        binding.textLoginLink.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Proses Registrasi
        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val nama = binding.tilNama.editText?.text.toString().trim()
        val nim = binding.tilNim.editText?.text.toString().trim()
        val email = binding.tilEmail.editText?.text.toString().trim()
        val phone = binding.tilPhone.editText?.text.toString().trim()
        val password = binding.tilPassword.editText?.text.toString()
        val confirmPassword = binding.tilConfirmPassword.editText?.text.toString()

        // Reset error sebelumnya
        binding.tilNama.error = null
        binding.tilNim.error = null
        binding.tilEmail.error = null
        binding.tilPhone.error = null
        binding.tilPassword.error = null
        binding.tilConfirmPassword.error = null

        when {
            nama.isEmpty() -> binding.tilNama.error = "Nama Lengkap wajib diisi"
            nim.isEmpty() -> binding.tilNim.error = "NIM wajib diisi"
            email.isEmpty() -> binding.tilEmail.error = "Email wajib diisi"
            !email.endsWith("@uniku.ac.id") -> binding.tilEmail.error = "Gunakan email kampus @uniku.ac.id"
            phone.isEmpty() -> binding.tilPhone.error = "No. Telepon wajib diisi"
            password.length < 6 -> binding.tilPassword.error = "Password minimal 6 karakter"
            confirmPassword != password -> binding.tilConfirmPassword.error = "Konfirmasi password tidak sama"
            else -> {
                // Simpan ke SharedPreferences
                val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
                val editor = prefs.edit()

                // Cek apakah email sudah terdaftar
                if (prefs.contains("${email}_password")) {
                    Toast.makeText(this, "Email ini sudah terdaftar!", Toast.LENGTH_SHORT).show()
                    return
                }

                editor.apply {
                    putString("${email}_nama", nama)
                    putString("${email}_nim", nim)
                    putString("${email}_phone", phone)
                    putString("${email}_password", password)  // plain text untuk prototype
                    apply()
                }

                Toast.makeText(this, "Registrasi berhasil! Silakan masuk", Toast.LENGTH_LONG).show()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}