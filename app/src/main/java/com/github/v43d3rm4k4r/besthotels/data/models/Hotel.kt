package com.github.v43d3rm4k4r.besthotels.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * "id": 313499,
"name": "Dream Downtown",
"address": "355 West 16th Street",
"stars": 0,
"distance": 716.06,
"suites_availability": "2:87:24:65:26:119:202:6"
 */

@Keep
data class Hotel(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Int,
    val distance: Double,
    @SerializedName("suites_availability") val suitesAvailability: String // contains the numbers of available rooms of the hotel, separated by a colon
)