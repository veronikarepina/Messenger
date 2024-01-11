package com.example.messengerapplication.presentation.main.main_screen.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.messengerapplication.base.BaseViewModel
import com.example.messengerapplication.presentation.main.main_screen.event.MainUiEvent
import com.example.messengerapplication.presentation.main.main_screen.state.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
): BaseViewModel<MainUiState, MainUiEvent, MainViewModelEvent>() {
    override val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState())
    override val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    override val _vmEvent: MutableSharedFlow<MainViewModelEvent> = MutableSharedFlow()
    override val vmEvent: SharedFlow<MainViewModelEvent> = _vmEvent.asSharedFlow()


    override fun postUiEvent(event: MainUiEvent) {
        when(event) {
            is MainUiEvent.ChangeRoute -> changeRoute(event.route)
        }
    }

    private fun changeRoute(route: String) {
        _uiState.value = MainUiState(selectedItemRoute = route)
        viewModelScope.launch {
            _vmEvent.emit(MainViewModelEvent.ChangeRoute(route))
        }
    }
}