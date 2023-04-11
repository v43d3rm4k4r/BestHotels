package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import androidx.lifecycle.ViewModel
import com.github.v43d3rm4k4r.besthotels.data.models.Hotel
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.domain.contracts.Repository
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.FetchHotelsUseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.OpenHotelDetailsUseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SearchHotelUseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SortHotelsUseCase
import javax.inject.Inject

class HotelsViewModel @Inject constructor(
    private val fetchHotelsUseCase: FetchHotelsUseCase,
    private val openHotelDetailsUseCase: OpenHotelDetailsUseCase,
    private val searchHotelUseCase: SearchHotelUseCase,
    private val sortHotelsUseCase: SortHotelsUseCase,
) : ViewModel() {

    //val hotels = fetchHotelsUseCase().state

    fun handleOnHotelClick(hotel: HotelDetailed) {
        // TODO: navigate to HotelDetailsFragment OR send event to Fragment about this (see Melnik)

    }
}