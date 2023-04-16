package com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels

import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.domain.usecases.UseCase
import javax.inject.Inject

class SearchHotelsUseCase @Inject constructor() : UseCase {

    operator fun invoke(hotels: List<HotelDetailed>, query: String): List<HotelDetailed> =
        hotels.filter {
            it.name.lowercase().contains(query.lowercase())
        }
}