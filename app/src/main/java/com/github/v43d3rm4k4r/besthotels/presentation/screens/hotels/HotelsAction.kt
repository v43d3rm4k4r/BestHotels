package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import com.github.v43d3rm4k4r.androidutils.network.ConnectivityObserver
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed

sealed interface HotelsAction {

    class ShowHotelDetails(val hotel: HotelDetailed) : HotelsAction

    class ShowNetworkAvailability(val status: ConnectivityObserver.Status) : HotelsAction
}
