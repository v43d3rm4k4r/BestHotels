package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SortHotelsUseCase.SortStrategy

sealed interface HotelsEvent {

    class HotelClicked(val hotel: HotelDetailed) : HotelsEvent

    class SearchQueryPrepared(val query: String) : HotelsEvent

    class SortSelected(val sortStrategy: SortStrategy) : HotelsEvent

    object ClearSearchClicked : HotelsEvent
}