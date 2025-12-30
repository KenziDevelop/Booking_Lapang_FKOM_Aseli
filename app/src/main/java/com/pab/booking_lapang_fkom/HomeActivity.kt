package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Klik card menu cepat
//        binding.cardLapangan.setOnClickListener {
//            startActivity(Intent(this, BookingLapanganActivity::class.java)) // form booking lapangan
//        }

        binding.cardAula.setOnClickListener {
            startActivity(Intent(this, BookingAulaActivity::class.java))
        }

        binding.cardKelas.setOnClickListener {
            startActivity(Intent(this, BookingKelasActivity::class.java))
        }

        binding.cardTeknologi.setOnClickListener {
            startActivity(Intent(this, BookingTeknologiActivity::class.java))
        }

        // Setup Bottom Navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Sudah di home, ga perlu pindah
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
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Setup FAB + (tengah)
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        // Default selected = Home
        binding.bottomNavigation.selectedItemId = R.id.nav_home
    }
}