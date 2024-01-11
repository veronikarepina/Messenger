package com.example.messengerapplication.presentation.start_and_auth.screens.auth.viewModel

import androidx.lifecycle.viewModelScope
import com.example.messengerapplication.base.BaseViewModel
import com.example.messengerapplication.presentation.start_and_auth.screens.auth.event.AuthUiEvent
import com.example.messengerapplication.presentation.start_and_auth.screens.auth.state.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
): BaseViewModel<AuthUiState, AuthUiEvent, AuthViewModelEvent>() {
    override val _uiState: MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState.SignIn)
    override val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    override val _vmEvent: MutableSharedFlow<AuthViewModelEvent> = MutableSharedFlow()
    override val vmEvent: SharedFlow<AuthViewModelEvent> = _vmEvent


    override fun postUiEvent(event: AuthUiEvent) {
        when (event) {
            is AuthUiEvent.ChangeUiStateEvent -> changeUiState()
            is AuthUiEvent.SendAuthEvent -> {testing()}
            is AuthUiEvent.SendRegisterEvent -> {testing()}
        }
    }

    private fun changeUiState() {
        when (_uiState.value) {
            is AuthUiState.SignIn -> _uiState.value = AuthUiState.SignUp
            is AuthUiState.SignUp -> _uiState.value = AuthUiState.SignIn
        }
    }

    private fun testing() {
        viewModelScope.launch {
            _vmEvent.emit(AuthViewModelEvent.SuccessfulAuthEvent)
        }
    }

    private fun tryAuth() {

    }

    private fun tryRegister() {

    }
}