package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import androidx.lifecycle.ViewModel
import com.github.v43d3rm4k4r.besthotels.data.models.Hotel
import com.github.v43d3rm4k4r.besthotels.domain.contracts.Repository
import javax.inject.Inject

class HotelsViewModel @Inject constructor(
    private val repository: Repository<*>
) : ViewModel() {

    // TODO: load notes on init

    fun handleOnNoteClick(hotel: Hotel) {
        // TODO: navigate to HotelDetailsFragment OR send event to Fragment about this (see Melnik)

    }
}