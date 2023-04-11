package com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels

import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.domain.contracts.HotelsFetcher
import com.github.v43d3rm4k4r.besthotels.domain.usecases.UseCase
import javax.inject.Inject

class FetchHotelsUseCase @Inject constructor(
    private val hotelsFetcher: HotelsFetcher
) : UseCase {

    suspend operator fun invoke(): List<HotelDetailed>? {
        val details = hotelsFetcher.fetchHotelsList()?.map {
            hotelsFetcher.fetchHotelDetails(it.id)!!
        }
        return details
    }
}