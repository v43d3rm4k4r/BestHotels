package com.github.v43d3rm4k4r.besthotels.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * {
"id": 40611,
"name": "Belleclaire Hotel",
"address": "250 West 77th Street, Manhattan",
"stars": 3.0,
"distance": 100.0,
"image": "1.jpg",
"suites_availability": "1:44:21:87:99:34",
"lat": 40.78260000000000,
"lon": -73.98130000000000
}
 */

@Keep
data class HotelDetailed(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Int,
    val distance: Double,
    @SerializedName("image") val imageName: String,
    @SerializedName("suites_availability") val suitesAvailability: String, // contains the numbers of available rooms of the hotel, separated by a colon
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double
)