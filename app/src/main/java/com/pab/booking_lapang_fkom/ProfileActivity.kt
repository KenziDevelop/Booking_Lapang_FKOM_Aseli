package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tombol Kembali (arrow back) -> kembali ke Home
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // 2. Klik Pengaturan -> ke activity_pengaturan.xml
        binding.itemPengaturan.setOnClickListener {
            startActivity(Intent(this, PengaturanActivity::class.java))
        }

        // 3. Klik Keluar -> logout dan kembali ke login.xml
        binding.itemKeluar.setOnClickListener {
            // Hapus status login
            getSharedPreferences("app_prefs", MODE_PRIVATE)
                .edit()
                .clear() // atau .putBoolean("onboarding_done", false).apply() kalau mau reset onboarding juga
                .apply()

            // Pindah ke login
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // clear semua activity sebelumnya
            startActivity(intent)
            finish()
        }

        // Bantuan & FAQ (opsional, kalau mau)
        binding.itemBantuan.setOnClickListener {
            // Bisa buka FAQ atau web
            // Toast.makeText(this, "Fitur Bantuan sedang dikembangkan", Toast.LENGTH_SHORT).show()
        }
    }
}