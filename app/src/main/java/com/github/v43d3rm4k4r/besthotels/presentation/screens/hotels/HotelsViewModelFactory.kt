package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.FetchHotelsUseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SearchHotelUseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SortHotelsUseCase
import javax.inject.Inject

class HotelsViewModelFactory @Inject constructor(
    private val fetchHotelsUseCase: FetchHotelsUseCase,
    private val searchHotelUseCase: SearchHotelUseCase,
    private val sortHotelsUseCase: SortHotelsUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        HotelsViewModel(fetchHotelsUseCase, searchHotelUseCase, sortHotelsUseCase) as T
}