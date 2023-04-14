package com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels

import android.util.Log
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.domain.contracts.HotelsFetcher
import com.github.v43d3rm4k4r.besthotels.domain.usecases.UseCase
import javax.inject.Inject

class FetchHotelsUseCase @Inject constructor(
    private val hotelsFetcher: HotelsFetcher
) : UseCase {

    suspend operator fun invoke(): List<HotelDetailed>? {
        val details = hotelsFetcher.fetchHotelsList()?.map {
            hotelsFetcher.fetchHotelDetails(it.id)?.apply {
                if (imageName != null) {
                    imageBitmap = hotelsFetcher.fetchHotelImageBytes(imageName)
                    if (imageBitmap == null) {
                        Log.w(TAG, "Failed to load image $imageName of hotel with id ${it.id}")
                    }
                }
            } ?: return null
        }
        return details
    }

    private companion object {
        const val TAG = "FetchHotelsUseCase"
    }
}