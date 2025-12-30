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

        // Tombol Kembali (ImageButton)
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Link "Sudah punya akun? Masuk"
        binding.textLoginLink.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Tombol Daftar (yang teksnya masih "Masuk" — kita ubah jadi "Daftar")
        binding.btnRegister.text = "Daftar"  // ubah teks tombol jadi "Daftar"

        binding.btnRegister.setOnClickListener {
            val nama = binding.tilNama.editText?.text.toString().trim()
            val nim = binding.tilNim.editText?.text.toString().trim()
            val email = binding.tilEmail.editText?.text.toString().trim()
            val phone = binding.tilPhone.editText?.text.toString().trim()
            val password = binding.tilPassword.editText?.text.toString()
            val confirmPassword = binding.tilConfirmPassword.editText?.text.toString()

            // Validasi semua field wajib diisi
            when {
                nama.isEmpty() -> {
                    binding.tilNama.error = "Nama wajib diisi"
                    return@setOnClickListener
                }
                nim.isEmpty() -> {
                    binding.tilNim.error = "NIM wajib diisi"
                    return@setOnClickListener
                }
                email.isEmpty() -> {
                    binding.tilEmail.error = "Email wajib diisi"
                    return@setOnClickListener
                }
                phone.isEmpty() -> {
                    binding.tilPhone.error = "No. Telepon wajib diisi"
                    return@setOnClickListener
                }
                password.isEmpty() -> {
                    binding.tilPassword.error = "Password wajib diisi"
                    return@setOnClickListener
                }
                confirmPassword.isEmpty() -> {
                    binding.tilConfirmPassword.error = "Konfirmasi password wajib diisi"
                    return@setOnClickListener
                }
                password != confirmPassword -> {
                    binding.tilConfirmPassword.error = "Password tidak sama"
                    return@setOnClickListener
                }
                else -> {
                    // Semua validasi lolos
                    binding.tilNama.error = null
                    binding.tilNim.error = null
                    binding.tilEmail.error = null
                    binding.tilPhone.error = null
                    binding.tilPassword.error = null
                    binding.tilConfirmPassword.error = null

                    // Tampilkan notif berhasil
                    Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_LONG).show()

                    // Pindah ke login
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}