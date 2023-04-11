package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.github.v43d3rm4k4r.besthotels.appComponent
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.databinding.FragmentHotelsBinding
import com.github.v43d3rm4k4r.besthotels.presentation.BaseFragment
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.recyclerviewutils.HotelsAdapter
import com.github.v43d3rm4k4r.kotlinutils.fastLazy
import kotlinx.coroutines.launch

class HotelsFragment : BaseFragment<FragmentHotelsBinding, HotelsViewModel, HotelsViewModelFactory>(
    FragmentHotelsBinding::inflate
) {
    override fun viewModelClass() = HotelsViewModel::class.java

    private val adapter by fastLazy { HotelsAdapter(viewModel::handleOnHotelClick) }
//    private val menuProvider by fastLazy { CrimeListMenuProvider(navigator(), crimeListViewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // requireActivity().addMenuProvider(menuProvider)
        setupUI()
    }

    private fun observeHotels() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(STARTED) {
                // TODO: viewModel.hotelsState.collect(::handleEvent)
            }
        }
    }

    private fun handleEvent() {

    }

    private fun setupUI() {
        with(binding.hotelsRecyclerView) {
            adapter = this@HotelsFragment.adapter

            //val callback = SimpleItemTouchHelperCallback(requireContext().resources, this@CrimeListFragment.adapter)
            //val touchHelper = ItemTouchHelper(callback)
            //touchHelper.attachToRecyclerView(this)

            //val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
            //addItemDecoration(dividerItemDecoration)

            // TODO: add itemAnimator
        }
    }

    private fun handleOnHotelClick(hotel: HotelDetailed) {
        val direction = HotelsFragmentDirections.actionHotelsFragmentToHotelDetailsFragment(hotel)
        findNavController().navigate(direction) // TODO: add anim options
    }
}