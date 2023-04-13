package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed

sealed interface HotelsEvent {
    class HotelClicked(val hotel: HotelDetailed) : HotelsEvent
}