package com.github.v43d3rm4k4r.besthotels.presentation.screens.hoteldetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hoteldetails.ShowImageDetailsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

// TODO: DELETE
class HotelDetailsViewModelFactory @AssistedInject constructor(
    private val showImageDetailsUseCase: ShowImageDetailsUseCase, // TODO: OPTIONAL
    @Assisted private val hotel: HotelDetailed
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        HotelDetailsViewModel(showImageDetailsUseCase, hotel) as T
}