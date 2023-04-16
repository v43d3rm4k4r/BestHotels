package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuProvider
import com.github.v43d3rm4k4r.besthotels.R
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SortHotelsUseCase.SortStrategy.BY_AVAILABLE_ROOMS
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SortHotelsUseCase.SortStrategy.BY_DISTANCE_FROM_CENTER
import com.github.v43d3rm4k4r.besthotels.domain.usecases.hotels.SortHotelsUseCase.SortStrategy.BY_STARS
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsEvent.ClearSearchClicked
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsEvent.SearchQueryPrepared
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsEvent.SortSelected

/**
 * Menu provider for [HotelsFragment].
 */
class HotelsMenuProvider(
    private val obtainEvent: (HotelsEvent) -> Unit,
    private val closeKeyboard: () -> Unit,
) : MenuProvider {

    private var searchView: SearchView? = null

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.menu_item_search)
        searchView = (searchItem.actionView as SearchView).apply {
            setOnQueryTextListener(object : OnQueryTextListener {

                override fun onQueryTextSubmit(query: String): Boolean {
                    closeKeyboard()
                    onActionViewCollapsed()
                    obtainEvent(SearchQueryPrepared(query))
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })
        }
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
        when (menuItem.itemId) {
            R.id.action_reset_search -> {
                obtainEvent(ClearSearchClicked)
                searchView?.isIconified = true
                true
            }
            R.id.action_sort_by_available_rooms -> {
                obtainEvent(SortSelected(BY_AVAILABLE_ROOMS))
                true
            }
            R.id.action_sort_by_distance -> {
                obtainEvent(SortSelected(BY_DISTANCE_FROM_CENTER))
                true
            }
            R.id.action_sort_by_stars -> {
                obtainEvent(SortSelected(BY_STARS))
                true
            }
            else -> false
        }
}