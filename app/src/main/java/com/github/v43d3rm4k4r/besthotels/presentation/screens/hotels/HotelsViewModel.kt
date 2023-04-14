package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import androidx.lifecycle.viewModelScope
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.FetchHotelsUseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SearchHotelUseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SortHotelsUseCase
import com.github.v43d3rm4k4r.besthotels.presentation.BaseViewModel
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsAction.ShowHotelDetails
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsEvent.HotelClicked
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelsViewModel @Inject constructor(
    private val fetchHotelsUseCase: FetchHotelsUseCase,
    private val searchHotelUseCase: SearchHotelUseCase,
    private val sortHotelsUseCase: SortHotelsUseCase,
) : BaseViewModel<HotelsState, HotelsAction, HotelsEvent>() {

    init {
        viewModelScope.launch(Dispatchers.IO) {// TODO: inject dispatcher
            viewState = HotelsState(emptyList(), isLoaded = false, failedToLoad = false)
            val hotels = fetchHotelsUseCase()
            viewState = if (hotels == null) {
                HotelsState(emptyList(), isLoaded = false, failedToLoad = true)
            } else {
                HotelsState(
                    hotels = hotels,
                    isLoaded = true
                )
            }
        }
    }

    override fun obtainEvent(viewEvent: HotelsEvent) =
        when (viewEvent) {
            is HotelClicked -> {
                viewAction = ShowHotelDetails(viewEvent.hotel)
            }
        }
}