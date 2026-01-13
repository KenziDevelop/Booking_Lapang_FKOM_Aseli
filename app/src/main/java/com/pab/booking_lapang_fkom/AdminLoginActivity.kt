package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.LoginAdminBinding

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Masuk Sebagai Admin -> ke activity_admin_home.xml
        binding.btnAdminLogin.setOnClickListener {
            val intent = Intent(this, AdminHomeActivity::class.java)
            startActivity(intent)
            finish() // biar ga bisa kembali
        }

        // ke Login Mahasiswa -> ke login.xml
        binding.textBackToStudent.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}