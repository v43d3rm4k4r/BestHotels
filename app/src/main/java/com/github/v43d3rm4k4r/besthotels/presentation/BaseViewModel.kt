package com.github.v43d3rm4k4r.besthotels.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * An MVI base [ViewModel] class.
 *
 * [Event]s come from the view (by calling [obtainEvent]) and ViewModel creates a [State] either [Action].
 * [Action] is some kind of UI activity that the fragment should perform.
 * [State] displays the current state of the UI.
 */
abstract class BaseViewModel<State, Action, Event>(initialState: State) : ViewModel() {

    protected val TAG = BaseViewModel::class.java.simpleName

    private val _viewStates = MutableStateFlow(initialState)
    private val _viewActions = Channel<Action>(capacity = Channel.UNLIMITED)

    val viewState = _viewStates.asStateFlow()
    val viewActions = _viewActions.receiveAsFlow()

    protected fun sendAction(action: Action) {
        viewModelScope.launch {
            _viewActions.send(action)
        }
    }

    protected fun updateState(state: State) {
        _viewStates.value = state
    }

    abstract fun obtainEvent(viewEvent: Event)
}
