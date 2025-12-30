package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pab.booking_lapang_fkom.databinding.ActivityPersetujuanBookingBinding

class PersetujuanBookingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersetujuanBookingBinding
    private val pendingBookings = mutableListOf<BookingModel>()
    private lateinit var adapter: PendingBookingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersetujuanBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Kembali -> ke Admin Home
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, AdminHomeActivity::class.java))
            finish()
        }

        loadPendingBookings()

        adapter = PendingBookingAdapter(pendingBookings,
            onApprove = { booking -> confirmAction(booking, "setujui") },
            onReject = { booking -> confirmAction(booking, "tolak") }
        )

        binding.recyclerPending.layoutManager = LinearLayoutManager(this)
        binding.recyclerPending.adapter = adapter

        updateStats()
    }

    private fun loadPendingBookings() {
        val prefs = getSharedPreferences("booking_prefs", MODE_PRIVATE)
        val saved = prefs.getStringSet("bookings", emptySet()) ?: emptySet()

        pendingBookings.clear()
        saved.forEach { str ->
            val parts = str.split("\n")
            if (parts.size >= 3 && parts.getOrNull(3)?.contains("Pending") == true) {
                val lapangan = parts[0].removePrefix("Lapangan: ")
                val tanggal = parts[1].removePrefix("Tanggal: ")
                val waktu = parts[2].removePrefix("Waktu: ")
                pendingBookings.add(BookingModel(lapangan, tanggal, waktu, status = "Pending"))
            }
        }
    }

    private fun confirmAction(booking: BookingModel, action: String) {
        val message = if (action == "setujui") "menyetujui" else "menolak"
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi")
            .setMessage("Apakah Anda yakin ingin $message pemesanan ini?")
            .setPositiveButton("Ya") { _, _ ->
                if (action == "setujui") {
                    booking.status = "Disetujui"
                } else {
                    booking.status = "Batal"
                }
                saveAllBookings()
                adapter.removeItem(booking)
                updateStats()
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    private fun saveAllBookings() {
        // Simpan ulang semua booking dengan status terbaru
        val prefs = getSharedPreferences("booking_prefs", MODE_PRIVATE)
        val editor = prefs.edit()
        val set = mutableSetOf<String>()
        // Tambah booking lain yang sudah ada (non-pending)
        // Untuk sekarang kita simpan ulang pending yang sudah diupdate
        // Nanti bisa diperbaiki dengan simpan semua booking di list terpisah
        editor.putStringSet("bookings", set)
        editor.apply()
    }

    private fun updateStats() {
        // Update statistik dinamis (hitung dari data)
        // Kamu bisa tambah ID untuk TextView di card statistik
    }
}