package com.github.v43d3rm4k4r.besthotels.domain.contracts

import android.graphics.Bitmap
import com.github.v43d3rm4k4r.besthotels.data.models.Hotel
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import retrofit2.Response

interface HotelsFetcher {

    suspend fun fetchHotelsList(): List<Hotel>?

    suspend fun fetchHotelDetails(id: Int): HotelDetailed?

    suspend fun fetchHotelImageBytes(imageName: String): Bitmap?
}