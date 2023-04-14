package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.github.v43d3rm4k4r.besthotels.R
import com.github.v43d3rm4k4r.besthotels.appComponent
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.databinding.FragmentHotelsBinding
import com.github.v43d3rm4k4r.besthotels.presentation.BaseFragment
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsAction.ShowHotelDetails
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.recyclerviewutils.HotelsAdapter
import com.github.v43d3rm4k4r.kotlinutils.fastLazy
import kotlinx.coroutines.launch

class HotelsFragment : BaseFragment<FragmentHotelsBinding, HotelsViewModel, HotelsViewModelFactory>(
    FragmentHotelsBinding::inflate
) {
    override fun viewModelClass() = HotelsViewModel::class.java

    private val recyclerAdapter by fastLazy { HotelsAdapter(viewModel::obtainEvent) }
//    private val menuProvider by fastLazy { CrimeListMenuProvider(navigator(), crimeListViewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // requireActivity().addMenuProvider(menuProvider)
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
                    recyclerAdapter.submitList(viewState.hotels)
                    binding.bottomTextView.isVisible = false
                }
                binding.progressBar.isVisible = !viewState.isLoaded
            }
        }
    }

    private fun bindViewAction(viewAction: HotelsAction) =
        when (viewAction) {
            is ShowHotelDetails -> navigateToHotelDetails(viewAction.hotel)
        }

    private fun setupUI() {
        with(binding.hotelsRecyclerView) {
            adapter = this@HotelsFragment.recyclerAdapter
        }
    }

    private fun navigateToHotelDetails(hotel: HotelDetailed) {
        val direction = HotelsFragmentDirections.actionHotelsFragmentToHotelDetailsFragment(hotel)
        findNavController().navigate(direction) // TODO: add anim options
    }

    // TODO: set menu provider
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}