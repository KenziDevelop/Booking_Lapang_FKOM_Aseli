package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.ActivityBookingBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.widget.TextView

class BookingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookingBinding
    private var selectedTime: String = ""
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Default tanggal hari ini
        binding.textTanggal.text = dateFormat.format(Date())

        // Setup klik waktu
        val timeCards = listOf(
            binding.time1 to "08.00 - 10.00",
            binding.time2 to "10.00 - 12.00",
            binding.time3 to "12.00 - 14.00",
            binding.time4 to "14.00 - 16.00",
            binding.time5 to "16.00 - 18.00",
            binding.time6 to "18.00 - 20.00"
        )

        timeCards.forEach { (card, time) ->
            card.setOnClickListener {
                // Reset semua card
                timeCards.forEach { it.first.setCardBackgroundColor(android.graphics.Color.parseColor("#FFFFFF")) }

                // Highlight yang dipilih
                card.setCardBackgroundColor(android.graphics.Color.parseColor("#001F3F"))
                // Lakukan casting ke TextView sebelum mengubah warna teks
                (card.getChildAt(0) as TextView).setTextColor(android.graphics.Color.WHITE)

                selectedTime = time
            }
        }

        // Tombol Ajukan Booking
        binding.btnAjukanBooking.setOnClickListener {
            val lapangan = binding.textLapangan.text.toString()
            val tanggal = binding.textTanggal.text.toString()

            if (selectedTime.isEmpty()) {
                Toast.makeText(this, "Pilih waktu terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simpan ke SharedPreferences (sementara)
            val prefs = getSharedPreferences("booking_prefs", MODE_PRIVATE)
            val editor = prefs.edit()

            val bookingData = "Lapangan: $lapangan\nTanggal: $tanggal\nWaktu: $selectedTime\nStatus: Pending"

            // Simpan sebagai list sederhana (kita tambah ke list existing)
            val currentBookings = prefs.getStringSet("bookings", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
            currentBookings.add(bookingData)
            editor.putStringSet("bookings", currentBookings)
            editor.apply()

            Toast.makeText(this, "Booking berhasil diajukan!", Toast.LENGTH_LONG).show()

            // Kembali ke home atau riwayat
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // Tombol Batal
        binding.btnBatal.setOnClickListener {
            finish() // kembali ke screen sebelumnya
        }

        // Tombol Kembali
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}