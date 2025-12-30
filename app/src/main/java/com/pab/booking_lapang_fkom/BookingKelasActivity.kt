package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.ActivityBookingKelasBinding

class BookingKelasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookingKelasBinding
    private var selectedKelas: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingKelasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Kembali ke home
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Klik item kelas
        val kelasItems = listOf(
            binding.itemKelas1 to "Lab Komputer 1",
            binding.itemKelas2 to "Lab Komputer 2",
            binding.itemKelas3 to "Lab Komputer 3",
            binding.itemKelas4 to "Lab Komputer 4",
            binding.itemKelas5 to "Lab Komputer 5"
        )

        kelasItems.forEach { (view, name) ->
            view.setOnClickListener {
                // Reset semua
                kelasItems.forEach { it.first.setBackgroundResource(R.drawable.bg_field_selector) }

                // Highlight yang dipilih
                view.setBackgroundResource(R.drawable.bg_field_selected)

                selectedKelas = name

                // Aktifkan tombol
                binding.btnAjukan.isEnabled = true
                binding.btnAjukan.setBackgroundTintList(getColorStateList(R.color.dark_blue)) // #001F3F
            }
        }

        // Tombol Ajukan -> ke Riwayat
        binding.btnAjukan.setOnClickListener {
            if (selectedKelas == null) {
                Toast.makeText(this, "Pilih kelas terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Booking $selectedKelas berhasil diajukan!", Toast.LENGTH_LONG).show()

            startActivity(Intent(this, RiwayatBookingActivity::class.java))
            finish()
        }
    }
}