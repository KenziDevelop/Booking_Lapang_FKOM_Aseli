package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.ActivityBookingAulaBinding

class BookingAulaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookingAulaBinding
    private var selectedAula: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingAulaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Kembali ke home
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Klik item aula
        val aulaItems = listOf(
            binding.itemAula1 to "Aula Gedung FKOM",
            binding.itemAula2 to "Aula Gedung Universitas Kuningan",
            binding.itemAula3 to "Aula Serbaguna Student Center"
        )

        aulaItems.forEach { (view, name) ->
            view.setOnClickListener {
                // Reset semua
                aulaItems.forEach { it.first.setBackgroundResource(R.drawable.bg_field_selector) }

                // Highlight yang dipilih
                view.setBackgroundResource(R.drawable.bg_field_selected) // buat drawable ini nanti

                selectedAula = name

                // Aktifkan tombol ajukan
                binding.btnAjukan.isEnabled = true
                binding.btnAjukan.setBackgroundTintList(getColorStateList(R.color.dark_blue)) // #001F3F
            }
        }

        // Tombol Ajukan -> ke Riwayat
        binding.btnAjukan.setOnClickListener {
            if (selectedAula == null) {
                Toast.makeText(this, "Pilih aula terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Booking $selectedAula berhasil diajukan!", Toast.LENGTH_LONG).show()

            // Pindah ke Riwayat
            startActivity(Intent(this, RiwayatBookingActivity::class.java))
            finish()
        }
    }
}