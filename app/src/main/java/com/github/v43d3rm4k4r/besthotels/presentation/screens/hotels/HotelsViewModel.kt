package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.FetchHotelsUseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SearchHotelUseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SortHotelsUseCase
import com.github.v43d3rm4k4r.besthotels.presentation.BaseViewModel
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsAction.ShowHotelDetails
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsEvent.HotelClicked
import javax.inject.Inject

class HotelsViewModel @Inject constructor(
    private val fetchHotelsUseCase: FetchHotelsUseCase,
    private val searchHotelUseCase: SearchHotelUseCase,
    private val sortHotelsUseCase: SortHotelsUseCase,
) : BaseViewModel<HotelsState, HotelsAction, HotelsEvent>() {

    //val hotels = fetchHotelsUseCase().state

    init {
        // TODO: DELETE THIS AFTER RECYCLER TESTS
        viewState = HotelsState(listOf(
            HotelDetailed(
            id = 123,
            address = "Some address",
            name = "Some name",
            stars = 5,
            distance = 5.0,
            imageName = "Image name",
            suitesAvailability = "5:10",
            latitude = 12.4,
            longitude = 45.5)
        ), isLoaded = true)
    }

    override fun obtainEvent(viewEvent: HotelsEvent) =
        when (viewEvent) {
            is HotelClicked -> {
                // TODO: send action
                viewAction = ShowHotelDetails(viewEvent.hotel)
            }
        }
}