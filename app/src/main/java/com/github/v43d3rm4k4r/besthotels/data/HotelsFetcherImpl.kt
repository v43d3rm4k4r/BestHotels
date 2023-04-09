package com.github.v43d3rm4k4r.besthotels.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.github.v43d3rm4k4r.besthotels.data.hotelsapi.HotelsAPI
import com.github.v43d3rm4k4r.besthotels.data.models.Hotel
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.di.HotelsFetcherDispatcher
import com.github.v43d3rm4k4r.besthotels.domain.contracts.HotelsFetcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HotelsFetcherImpl @Inject constructor(
    private val hotelsAPI: HotelsAPI,
    @HotelsFetcherDispatcher private val defaultDispatcher: CoroutineDispatcher
) : HotelsFetcher {

    override suspend fun fetchHotelsList(): List<Hotel>? = withContext(defaultDispatcher) {
        val response = hotelsAPI.fetchHotelsList()
        if (!response.isSuccessful) return@withContext null
        response.body()!!
    }

    override suspend fun fetchHotelDetails(id: Int): HotelDetailed? = withContext(defaultDispatcher) {
        val response = hotelsAPI.fetchHotelDetails(id.toString())
        if (!response.isSuccessful) return@withContext null
        response.body()!!
    }

    override suspend fun fetchHotelImageBytes(imageName: String): Bitmap? = withContext(defaultDispatcher) {
        val response = hotelsAPI.fetchHotelImageBytes(imageName)
        if (!response.isSuccessful) return@withContext null
        response.body()?.byteStream()?.use( BitmapFactory::decodeStream)
    }
}