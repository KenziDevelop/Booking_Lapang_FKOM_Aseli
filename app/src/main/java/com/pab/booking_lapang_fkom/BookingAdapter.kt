package com.pab.booking_lapang_fkom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pab.booking_lapang_fkom.databinding.ItemBookingBinding

class BookingAdapter(private val bookings: List<BookingModel>) : RecyclerView.Adapter<BookingAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemBookingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val booking = bookings[position]
        with(holder.binding) {
            textLapangan.text = booking.lapangan
            textTanggal.text = "Tanggal : ${booking.tanggal}"
            textWaktu.text = "Waktu : ${booking.waktu}"
            textAcara.text = "Acara : ${booking.acara}"

            // Status
            textStatus.text = booking.status
            when (booking.status) {
                "Aktif" -> {
                    textStatus.setBackgroundResource(R.drawable.bg_status_green)
                    textStatus.setTextColor(holder.itemView.context.getColor(android.R.color.white))
                }
                "Selesai" -> {
                    textStatus.setBackgroundResource(R.drawable.bg_status_blue)
                    textStatus.setTextColor(holder.itemView.context.getColor(android.R.color.white))
                }
                "Batal" -> {
                    textStatus.setBackgroundResource(R.drawable.bg_status_red)
                    textStatus.setTextColor(holder.itemView.context.getColor(android.R.color.white))
                }
                else -> { // Pending
                    textStatus.setBackgroundResource(R.drawable.bg_status_orange)
                    textStatus.setTextColor(holder.itemView.context.getColor(android.R.color.white))
                }
            }
        }
    }

    override fun getItemCount() = bookings.size
}