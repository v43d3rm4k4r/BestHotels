package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.v43d3rm4k4r.androidutils.network.ConnectivityObserver
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.FetchHotelsUseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SearchHotelsUseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SortHotelsUseCase
import javax.inject.Inject

class HotelsViewModelFactory @Inject constructor(
    private val fetchHotelsUseCase: FetchHotelsUseCase,
    private val searchHotelUseCase: SearchHotelsUseCase,
    private val sortHotelsUseCase: SortHotelsUseCase,
    private val connectivityObserver: ConnectivityObserver,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        HotelsViewModel(fetchHotelsUseCase, searchHotelUseCase, sortHotelsUseCase, connectivityObserver) as T
}