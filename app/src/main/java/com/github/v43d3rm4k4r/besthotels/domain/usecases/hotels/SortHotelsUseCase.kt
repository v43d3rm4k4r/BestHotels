package com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels

import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.domain.usecases.UseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SortHotelsUseCase.SortStrategy.BY_AVAILABLE_ROOMS
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SortHotelsUseCase.SortStrategy.BY_DISTANCE_FROM_CENTER
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SortHotelsUseCase.SortStrategy.BY_STARS
import javax.inject.Inject

class SortHotelsUseCase @Inject constructor() : UseCase {

    operator fun invoke(hotels: List<HotelDetailed>, sortStrategy: SortStrategy): List<HotelDetailed> =
        when (sortStrategy) {
            BY_DISTANCE_FROM_CENTER -> hotels.sortedBy { it.distance }
            BY_AVAILABLE_ROOMS -> hotels.sortedByDescending { it.suitesAvailability.split(':').size }
            BY_STARS -> hotels.sortedByDescending { it.stars }
        }

    enum class SortStrategy {
        BY_DISTANCE_FROM_CENTER,
        BY_AVAILABLE_ROOMS,
        BY_STARS
    }
}