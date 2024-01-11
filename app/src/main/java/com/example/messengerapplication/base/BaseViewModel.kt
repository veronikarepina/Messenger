package com.example.messengerapplication.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<UiState : BaseUiState, UiEvent : BaseUiEvent, VMEvent : BaseViewModelEvent> : ViewModel() {

    protected abstract val _uiState: MutableStateFlow<UiState>
    abstract val uiState: StateFlow<UiState>

    protected abstract val _vmEvent: MutableSharedFlow<VMEvent>
    abstract val vmEvent: SharedFlow<VMEvent>


    abstract fun postUiEvent(event: UiEvent)
}