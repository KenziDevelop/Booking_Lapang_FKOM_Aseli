package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.LoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Masuk
        binding.btnLogin.setOnClickListener {
            performLogin()
        }

        // Link "Belum punya akun? Daftar"
        binding.textRegisterLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        // Login Sebagai Admin (sementara placeholder)
        binding.textAdminLogin.setOnClickListener {
            Toast.makeText(this, "Fitur login admin belum tersedia", Toast.LENGTH_SHORT).show()
            // nanti bisa buat AdminLoginActivity kalau perlu
        }

        // Lupa Password (placeholder)
        binding.textForgotPassword.setOnClickListener {
            Toast.makeText(this, "Fitur lupa password sedang dikembangkan", Toast.LENGTH_SHORT).show()
        }
    }

    private fun performLogin() {
        val email = binding.tilEmail.editText?.text.toString().trim()
        val password = binding.tilPassword.editText?.text.toString()

        // Reset error
        binding.tilEmail.error = null
        binding.tilPassword.error = null

        if (email.isEmpty()) {
            binding.tilEmail.error = "Email wajib diisi"
            return
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "Password wajib diisi"
            return
        }

        val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
        val savedPassword = prefs.getString("${email}_password", null)

        if (savedPassword == null) {
            binding.tilEmail.error = "Email belum terdaftar"
            return
        }

        if (savedPassword == password) {
            // Login berhasil
            val nama = prefs.getString("${email}_nama", "Mahasiswa")
            Toast.makeText(this, "Selamat datang, $nama!", Toast.LENGTH_SHORT).show()

            // Simpan status sudah login (opsional, untuk auto-login nanti)
            prefs.edit().putBoolean("is_logged_in", true).apply()

            // Pindah ke Home
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            binding.tilPassword.error = "Password salah"
        }
    }
}