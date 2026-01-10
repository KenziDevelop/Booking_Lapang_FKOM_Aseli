package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.ActivityKalenderBookingBinding

class KalenderBookingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKalenderBookingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKalenderBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // tombol kembali
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Tombol buat booking
        binding.btnBuatBooking.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
            finish()
        }
    }
}