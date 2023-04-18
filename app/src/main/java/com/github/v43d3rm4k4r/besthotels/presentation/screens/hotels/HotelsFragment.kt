package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.androidutils.closeKeyboard
import com.bignerdranch.android.androidutils.showSnackbar
import com.github.v43d3rm4k4r.androidutils.network.ConnectivityObserver
import com.github.v43d3rm4k4r.androidutils.network.ConnectivityObserver.Status.AVAILABLE
import com.github.v43d3rm4k4r.androidutils.network.ConnectivityObserver.Status.LOST
import com.github.v43d3rm4k4r.besthotels.R
import com.github.v43d3rm4k4r.besthotels.appComponent
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.databinding.FragmentHotelsBinding
import com.github.v43d3rm4k4r.besthotels.presentation.BaseFragmentVM
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsAction.ShowHotelDetails
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsAction.ShowNetworkAvailability
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.recyclerviewutils.HotelsAdapter
import com.github.v43d3rm4k4r.kotlinutils.fastLazy
import kotlinx.coroutines.launch

class HotelsFragment : BaseFragmentVM<FragmentHotelsBinding, HotelsViewModel, HotelsViewModelFactory>(
    FragmentHotelsBinding::inflate
) {
    override fun viewModelClass() = HotelsViewModel::class.java

    private val recyclerAdapter by fastLazy { HotelsAdapter(viewModel::obtainEvent) }
    private val menuProvider by fastLazy { HotelsMenuProvider(viewModel::obtainEvent, ::closeKeyboard) }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(STARTED) {
                viewModel.viewStates().collect { state -> state?.let { bindViewState(it) } }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(STARTED) {
                viewModel.viewActions().collect { action -> action?.let { bindViewAction(it) } }
            }
        }
    }

    private fun bindViewState(viewState: HotelsState) {
        with(binding) {
            if (viewState.failedToLoad) {
                progressBar.isVisible = false
                hotelsRecyclerView.isVisible = false
                bottomTextView.isVisible = true
                bottomTextView.text = getString(R.string.hotels_failed_to_load)
            } else {
                if (viewState.isLoaded) {
                    if (viewState.hotels.isEmpty()) {
                        hotelsRecyclerView.isVisible = false
                        bottomTextView.isVisible = true
                        bottomTextView.text = if (!viewState.queryText.isNullOrBlank()) {
                            getString(R.string.no_hotels_arg_found, viewState.queryText)
                        } else {
                            getString(R.string.no_hotels_found)
                        }
                    } else {
                        hotelsRecyclerView.isVisible = true
                        recyclerAdapter.submitList(viewState.hotels)
                        bottomTextView.isVisible = false
                    }
                    if (viewState.isScrollToRecyclerStartNeeded) {
                        nestedScrollView.smoothScrollTo(0, hotelsRecyclerView.top)
                    }
                } else { // loading
                    bottomTextView.isVisible = false
                }
                progressBar.isVisible = !viewState.isLoaded
            }
        }
    }

    private fun bindViewAction(viewAction: HotelsAction) =
        when (viewAction) {
            is ShowHotelDetails -> navigateToHotelDetails(viewAction.hotel)
            is ShowNetworkAvailability -> showNetworkStatus(viewAction.status)
        }

    private fun setupUI() {
        with(binding) {
            hotelsRecyclerView.adapter = this@HotelsFragment.recyclerAdapter
        }
        requireActivity().addMenuProvider(menuProvider)
    }

    private fun navigateToHotelDetails(hotel: HotelDetailed) {
        val direction = HotelsFragmentDirections.actionHotelsFragmentToHotelDetailsFragment(hotel)
        findNavController().navigate(direction) // TODO: add anim options
    }

    private fun showNetworkStatus(status: ConnectivityObserver.Status) =
        when (status) {
            AVAILABLE -> getString(R.string.network_available)
            LOST -> getString(R.string.network_lost)
            else -> null
        }?.let { showSnackbar(it) }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().removeMenuProvider(menuProvider)
    }
}