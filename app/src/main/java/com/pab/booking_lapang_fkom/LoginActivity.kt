package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.LoginBinding  // binding dari login.xml

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Masuk -> ke activity_home.xml
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)  // nanti kita buat HomeActivity.kt
            startActivity(intent)
            finish()  // optional: tutup login biar ga bisa kembali
        }

        // Tombol Daftar -> ke register.xml
        binding.textRegisterLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)  // nanti buat RegisterActivity.kt
            startActivity(intent)
        }

        // Tombol Login Sebagai Admin -> ke login_admin.xml
        binding.textAdminLogin.setOnClickListener {
            val intent = Intent(this, AdminLoginActivity::class.java)  // nanti buat AdminLoginActivity.kt
            startActivity(intent)
        }

        // Lupa Password (opsional, bisa diisi nanti)
        binding.textForgotPassword.setOnClickListener {
            // Toast atau dialog "Fitur sedang dikembangkan"
            // Toast.makeText(this, "Fitur lupa password sedang dikembangkan", Toast.LENGTH_SHORT).show()
        }
    }
}