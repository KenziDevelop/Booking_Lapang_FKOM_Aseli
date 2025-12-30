package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.ActivityAdminProfileBinding

class AdminProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tombol Kembali (arrow back) -> kembali ke Admin Home
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, AdminHomeActivity::class.java))
            finish()
        }

        // 2. Klik Pengaturan -> ke activity_pengaturan.xml
        binding.itemPengaturan.setOnClickListener {
            startActivity(Intent(this, PengaturanActivity::class.java))
        }

        // 3. Klik Keluar -> logout dan kembali ke login.xml
        binding.itemKeluar.setOnClickListener {
            // Hapus status login admin
            getSharedPreferences("app_prefs", MODE_PRIVATE)
                .edit()
                .clear() // hapus semua data login
                .apply()

            // Pindah ke login mahasiswa dan bersihkan stack
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Bantuan & FAQ (opsional)
        binding.itemBantuan.setOnClickListener {
            // Bisa buka halaman FAQ atau toast sementara
            // Toast.makeText(this, "Bantuan & FAQ sedang dikembangkan", Toast.LENGTH_SHORT).show()
        }
    }
}