package com.github.v43d3rm4k4r.besthotels.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Hotel(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Int,
    val distance: Double,
    @SerializedName("suites_availability") val suitesAvailability: String // contains the numbers of available rooms of the hotel, separated by a colon
) : Parcelable