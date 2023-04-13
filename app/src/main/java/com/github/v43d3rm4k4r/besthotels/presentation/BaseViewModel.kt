package com.github.v43d3rm4k4r.besthotels.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * An MVI base view model class.
 *
 * [Event]s come from the screen (by calling [obtainEvent]) and creates a [State] either [Action].
 * [Action] is some kind of UI activity that the fragment should perform.
 * [State] displays the current state of the UI.
 */
abstract class BaseViewModel<State, Action, Event> : ViewModel() {

    private val TAG = BaseViewModel::class.java.simpleName

    private val _viewStates = MutableStateFlow<State?>(null)
    fun viewStates(): StateFlow<State?> = _viewStates

    private var _viewState: State? = null
    protected var viewState: State
        get() = _viewState
            ?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized")
        set(value) {
            /** StateFlow doesn't work with same values */
            if (_viewStates.value == value) {
                _viewStates.value = null
            }
            _viewStates.value = value
        }


    private val _viewActions = MutableStateFlow<Action?>(null)
    fun viewActions(): StateFlow<Action?> = _viewActions

    private var _viewAction: Action? = null
    protected var viewAction: Action
        get() = _viewAction
            ?: throw UninitializedPropertyAccessException("\"viewAction\" was queried before being initialized")
        set(value) {
            /** StateFlow doesn't work with same values */
            if (_viewActions.value == value) {
                _viewActions.value = null
            }
            _viewActions.value = value
        }

    abstract fun obtainEvent(viewEvent: Event)
}