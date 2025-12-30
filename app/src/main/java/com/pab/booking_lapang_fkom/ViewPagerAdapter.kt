package com.pab.booking_lapang_fkom

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fa: FragmentActivity, private val allBookings: List<BookingModel>) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        val filtered = when (position) {
            0 -> allBookings.filter { it.status == "Selesai" }
            1 -> allBookings.filter { it.status == "Pending" }
            2 -> allBookings.filter { it.status == "Dikonfirmasi" || it.status == "Aktif" }
            else -> allBookings
        }
        return RiwayatFragment.newInstance(ArrayList(filtered))
    }
}