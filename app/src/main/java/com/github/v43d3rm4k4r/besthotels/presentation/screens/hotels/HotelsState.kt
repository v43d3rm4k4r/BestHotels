package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed

class HotelsState(
    val hotels: List<HotelDetailed> = emptyList(),
    val isLoaded: Boolean = false,
    val failedToLoad: Boolean = false
)