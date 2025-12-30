package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.ActivityAdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tombol "Lihat semua permohonan" -> ke persetujuan booking
        binding.btnLihatPermohonan.setOnClickListener {
            startActivity(Intent(this, PersetujuanBookingActivity::class.java))
        }

        // 2. Bottom Navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Sudah di home admin, ga perlu pindah
                    true
                }
                R.id.nav_kalender -> {
                    startActivity(Intent(this, KalenderBookingActivity::class.java))
                    true
                }
                R.id.nav_riwayat -> {
                    startActivity(Intent(this, RiwayatBookingActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, AdminProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // 3. FAB + -> ke form booking (admin juga bisa buat booking)
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        // Default selected = Home
        binding.bottomNavigation.selectedItemId = R.id.nav_home
    }
}