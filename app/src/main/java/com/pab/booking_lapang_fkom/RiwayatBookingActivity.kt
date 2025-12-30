package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.pab.booking_lapang_fkom.databinding.ActivityRiwayatBookingBinding

class RiwayatBookingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatBookingBinding
    private val allBookings = mutableListOf<BookingModel>()
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tambahan: Tombol kembali ke Home
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        loadBookingsFromPrefs()

        // Setup ViewPager + Tab
        adapter = ViewPagerAdapter(this, allBookings)
        binding.viewPagerRiwayat.adapter = adapter

        TabLayoutMediator(binding.tabFilter, binding.viewPagerRiwayat) { tab, position ->
            tab.text = when (position) {
                0 -> "Selesai"
                1 -> "Pending"
                2 -> "Dikonfirmasi"
                else -> "All"
            }
        }.attach()

        updateStats()
    }

    private fun loadBookingsFromPrefs() {
        val prefs = getSharedPreferences("booking_prefs", MODE_PRIVATE)
        val saved = prefs.getStringSet("bookings", emptySet()) ?: emptySet()

        allBookings.clear()
        saved.forEach { str ->
            val parts = str.split("\n")
            if (parts.size >= 3) {
                val lapangan = parts[0].removePrefix("Lapangan: ")
                val tanggal = parts[1].removePrefix("Tanggal: ")
                val waktu = parts[2].removePrefix("Waktu: ")
                allBookings.add(BookingModel(lapangan, tanggal, waktu, status = "Pending"))
            }
        }
    }

    private fun updateStats() {
        val aktif = allBookings.count { it.status == "Aktif" }
        val selesai = allBookings.count { it.status == "Selesai" }
        val batal = allBookings.count { it.status == "Batal" }

        // Nanti kita update statistik dinamis
    }
}