package com.github.v43d3rm4k4r.besthotels.data.models

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class HotelDetailed(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Int,
    val distance: Double,
    @SerializedName("image") val imageName: String?,
    @SerializedName("suites_availability") val suitesAvailability: String,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
    var imageBitmap: Bitmap?
) : Parcelable