package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.ActivityBookingTeknologiBinding

class BookingTeknologiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookingTeknologiBinding
    private var selectedTeknologi: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingTeknologiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Kembali ke home
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Klik item teknologi
        val teknologiItems = listOf(
            binding.itemTeknologi1 to "LCD Proyektor",
            binding.itemTeknologi2 to "Laptop Mahasiswa",
            binding.itemTeknologi3 to "Game Permainan Virtual Reality",
            binding.itemTeknologi4 to "Speaker Portable"
        )

        teknologiItems.forEach { (view, name) ->
            view.setOnClickListener {
                // Reset semua
                teknologiItems.forEach { it.first.setBackgroundResource(R.drawable.bg_field_selector) }

                // Highlight yang dipilih
                view.setBackgroundResource(R.drawable.bg_field_selected)

                selectedTeknologi = name

                // Aktifkan tombol
                binding.btnAjukan.isEnabled = true
                binding.btnAjukan.setBackgroundTintList(getColorStateList(R.color.dark_blue)) // #001F3F
            }
        }

        // Tombol Ajukan -> ke Riwayat
        binding.btnAjukan.setOnClickListener {
            if (selectedTeknologi == null) {
                Toast.makeText(this, "Pilih perangkat terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Pinjaman $selectedTeknologi berhasil diajukan!", Toast.LENGTH_LONG).show()

            startActivity(Intent(this, RiwayatBookingActivity::class.java))
            finish()
        }
    }
}