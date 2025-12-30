package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.pab.booking_lapang_fkom.databinding.ActivityKalenderBookingBinding
import java.text.SimpleDateFormat
import java.util.*

class KalenderBookingActivity : AppCompatActivity(), OnDateSelectedListener {

    private lateinit var binding: ActivityKalenderBookingBinding
    private val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale("id", "ID"))
    private val allBookings = mutableListOf<BookingModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKalenderBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tombol Kembali -> ke Home
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // 2. Tombol Buat Booking Baru -> ke form booking
        binding.btnBuatBooking.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        // Setup Kalender
        binding.calendarView.setOnDateChangedListener(this)

        // Default hari ini
        val today = CalendarDay.today()
        binding.calendarView.selectedDate = today
        updateBookingInfo(today)

        // Load data booking dari SharedPreferences
        loadBookingsFromPrefs()
    }

    override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {
        updateBookingInfo(date)
    }

    private fun updateBookingInfo(date: CalendarDay) {
        val formattedDate = dateFormat.format(date.date)
        binding.textBookingDate.text = "Booking untuk $formattedDate"

        // Cari booking di tanggal ini
        val bookingsToday = allBookings.filter { it.tanggal == formattedDate }

        if (bookingsToday.isEmpty()) {
            binding.textNoBooking.text = "Tidak ada booking untuk hari ini"
            binding.textNoBooking.visibility = android.view.View.VISIBLE
        } else {
            // Kalau ada booking, tampilkan list (nanti bisa diganti RecyclerView)
            var listText = "Booking hari ini:\n"
            bookingsToday.forEach {
                listText += "• ${it.lapangan} | ${it.waktu} | ${it.status}\n"
            }
            binding.textNoBooking.text = listText.trim()
            binding.textNoBooking.visibility = android.view.View.VISIBLE
        }
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

        // Update info untuk tanggal hari ini
        updateBookingInfo(binding.calendarView.selectedDate ?: CalendarDay.today())
    }
}