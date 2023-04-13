package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
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
                viewModel.viewActions().collect { action -> action?.let { bindViewAction(it) } }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(STARTED) {
                viewModel.viewStates().collect { state -> state?.let { bindViewState(it) } }
            }
        }
    }

    private fun bindViewState(viewState: HotelsState) {
        recyclerAdapter.submitList(viewState.hotels)
        binding.progressBar.isVisible = !viewState.isLoaded
    }

    private fun bindViewAction(viewAction: HotelsAction) =
        when (viewAction) {
            is ShowHotelDetails -> navigateToHotelDetails(viewAction.hotel)
        }

    private fun setupUI() {
        with(binding.hotelsRecyclerView) {
            adapter = this@HotelsFragment.recyclerAdapter

            //val callback = SimpleItemTouchHelperCallback(requireContext().resources, this@CrimeListFragment.adapter)
            //val touchHelper = ItemTouchHelper(callback)
            //touchHelper.attachToRecyclerView(this)

            //val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
            //addItemDecoration(dividerItemDecoration)

            // TODO: add itemAnimator
        }
    }

    private fun navigateToHotelDetails(hotel: HotelDetailed) {
        val direction = HotelsFragmentDirections.actionHotelsFragmentToHotelDetailsFragment(hotel)
        findNavController().navigate(direction) // TODO: add anim options
    }
}