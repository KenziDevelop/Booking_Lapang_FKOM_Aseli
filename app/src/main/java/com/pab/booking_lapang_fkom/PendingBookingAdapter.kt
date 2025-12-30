package com.pab.booking_lapang_fkom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pab.booking_lapang_fkom.databinding.ItemBookingPendingBinding

class PendingBookingAdapter(
    private val bookings: MutableList<BookingModel>,
    private val onApprove: (BookingModel) -> Unit,
    private val onReject: (BookingModel) -> Unit
) : RecyclerView.Adapter<PendingBookingAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemBookingPendingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookingPendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val booking = bookings[position]
        with(holder.binding) {
            textLapangan.text = booking.lapangan
            textTanggal.text = "Tanggal : ${booking.tanggal}"
            textWaktu.text = "Waktu : ${booking.waktu}"
            textAcara.text = "Acara : ${booking.acara}"
            textStatus.text = booking.status

            btnSetujui.setOnClickListener { onApprove(booking) }
            btnTolak.setOnClickListener { onReject(booking) }
        }
    }

    override fun getItemCount() = bookings.size

    fun removeItem(booking: BookingModel) {
        val index = bookings.indexOf(booking)
        if (index != -1) {
            bookings.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}