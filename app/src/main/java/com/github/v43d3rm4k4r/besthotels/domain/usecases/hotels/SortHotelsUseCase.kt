package com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels

import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.domain.usecases.UseCase
import javax.inject.Inject

class SortHotelsUseCase @Inject constructor() : UseCase {

    operator fun invoke(sortStrategy: SortStrategy, hotels: List<HotelDetailed>) {
        // TODO
    }
}

enum class SortStrategy {
    BY_DISTANCE_FROM_CENTER,
    BY_AVAILABLE_ROOMS
}