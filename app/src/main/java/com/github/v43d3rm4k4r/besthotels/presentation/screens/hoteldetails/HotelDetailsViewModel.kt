package com.github.v43d3rm4k4r.besthotels.presentation.screens.hoteldetails

import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hoteldetails.ShowImageDetailsUseCase
import com.github.v43d3rm4k4r.besthotels.presentation.BaseViewModel
import javax.inject.Inject

// TODO: DELETE
class HotelDetailsViewModel @Inject constructor(
    private val showImageDetailsUseCase: ShowImageDetailsUseCase, // TODO: OPTIONAL
    private val hotel: HotelDetailed
) : BaseViewModel<HotelDetailsState, HotelDetailsAction, HotelDetailsEvent>() {

    override fun obtainEvent(viewEvent: HotelDetailsEvent) {
        TODO("Not yet implemented")
    }
}