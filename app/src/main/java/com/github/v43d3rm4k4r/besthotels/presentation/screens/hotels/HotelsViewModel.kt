package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import androidx.lifecycle.viewModelScope
import com.github.v43d3rm4k4r.androidutils.network.ConnectivityObserver
import com.github.v43d3rm4k4r.androidutils.network.ConnectivityObserver.Status.AVAILABLE
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.FetchHotelsUseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SearchHotelsUseCase
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SortHotelsUseCase
import com.github.v43d3rm4k4r.besthotels.presentation.BaseViewModel
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsAction.ShowHotelDetails
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsAction.ShowNetworkAvailability
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsEvent.ClearSearchClicked
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsEvent.HotelClicked
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsEvent.SearchQueryPrepared
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsEvent.SortSelected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelsViewModel @Inject constructor(
    private val fetchHotelsUseCase: FetchHotelsUseCase,
    private val searchHotelsUseCase: SearchHotelsUseCase,
    private val sortHotelsUseCase: SortHotelsUseCase,
    connectivityObserver: ConnectivityObserver,
) : BaseViewModel<HotelsState, HotelsAction, HotelsEvent>(HotelsState(failedToLoad = true)) {

    private var currentHotels: List<HotelDetailed>? = null
    private var isFirstAvailableStatus = true

    init {
        connectivityObserver.observe().onEach { status ->
            if (status == AVAILABLE) {
                updateState(HotelsState(isLoaded = false, failedToLoad = false))
                tryLoadHotels()
                if (isFirstAvailableStatus) {
                    isFirstAvailableStatus = false
                    return@onEach
                }
            }
            sendAction(ShowNetworkAvailability(status))
        }.launchIn(viewModelScope)
    }

    override fun obtainEvent(viewEvent: HotelsEvent) =
        when (viewEvent) {
            is HotelClicked -> handleHotelClicked(viewEvent)
            is SearchQueryPrepared -> handleSearchQueryPrepared(viewEvent)
            is SortSelected -> handleSortSelected(viewEvent)
            ClearSearchClicked -> handleClearSearchClicked()
        }

    private fun handleHotelClicked(viewEvent: HotelClicked) {
        sendAction(ShowHotelDetails(viewEvent.hotel))
    }

    private fun handleSearchQueryPrepared(viewEvent: SearchQueryPrepared) {
        if (currentHotels == null) return
        updateState(
            HotelsState(
                hotels = searchHotelsUseCase(currentHotels!!, viewEvent.query),
                isLoaded = true,
                queryText = viewEvent.query
            )
        )
    }

    private fun handleSortSelected(viewEvent: SortSelected) {
        if (currentHotels == null) return
        updateState(
            HotelsState(
                hotels = sortHotelsUseCase(currentHotels!!, viewEvent.sortStrategy),
                isLoaded = true,
                isScrollToRecyclerStartNeeded = true
            )
        )
    }

    private fun tryLoadHotels() {
        viewModelScope.launch(Dispatchers.IO) {
            currentHotels = fetchHotelsUseCase()
            val newState = if (currentHotels == null) {
                HotelsState(emptyList(), isLoaded = false, failedToLoad = true)
            } else {
                HotelsState(
                    hotels = currentHotels!!,
                    isLoaded = true
                )
            }
            updateState(newState)
        }
    }

    private fun handleClearSearchClicked() {
        if (currentHotels == null) return
        updateState(
            HotelsState(
                hotels = currentHotels!!,
                isLoaded = true
            )
        )
    }
}
