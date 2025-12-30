package com.pab.booking_lapang_fkom

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingModel(
    val lapangan: String,
    val tanggal: String,
    val waktu: String,
    val acara: String = "Olahraga",
    var status: String = "Pending"
) : Parcelable