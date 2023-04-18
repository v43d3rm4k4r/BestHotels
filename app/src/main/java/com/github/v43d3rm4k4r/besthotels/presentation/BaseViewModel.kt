package com.github.v43d3rm4k4r.besthotels.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * An MVI base [ViewModel] class.
 *
 * [Event]s come from the view (by calling [obtainEvent]) and ViewModel creates a [State] either [Action].
 * [Action] is some kind of UI activity that the fragment should perform.
 * [State] displays the current state of the UI.
 */
abstract class BaseViewModel<State, Action, Event> : ViewModel() {

    protected val TAG = BaseViewModel::class.java.simpleName

    private val _viewStates = MutableStateFlow<State?>(null)
    fun viewStates(): StateFlow<State?> = _viewStates

    private var _viewState: State? = null
    protected var viewState: State
        get() = _viewState
            ?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized")
        set(value) {
            _viewStates.value = value
        }

    private val _viewActions = MutableSharedFlow<Action?>()
    fun viewActions(): SharedFlow<Action?> = _viewActions

    private var _viewAction: Action? = null
    protected var viewAction: Action
        get() = _viewAction
            ?: throw UninitializedPropertyAccessException("\"viewAction\" was queried before being initialized")
        set(value) {
            viewModelScope.launch {
                _viewActions.emit(value)
            }
        }

    abstract fun obtainEvent(viewEvent: Event)
}