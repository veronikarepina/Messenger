package com.example.messengerapplication.presentation.start_and_auth.screens.auth.viewModel

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.messengerapplication.base.BaseViewModel
import com.example.messengerapplication.base.Response
import com.example.messengerapplication.domain.AuthRepository
import com.example.messengerapplication.presentation.start_and_auth.screens.auth.event.AuthUiEvent
import com.example.messengerapplication.presentation.start_and_auth.screens.auth.state.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel<AuthUiState, AuthUiEvent, AuthViewModelEvent>() {
    override val _uiState: MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState.SignIn())
    override val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    override val _vmEvent: MutableSharedFlow<AuthViewModelEvent> = MutableSharedFlow()
    override val vmEvent: SharedFlow<AuthViewModelEvent> = _vmEvent


    override fun postUiEvent(event: AuthUiEvent) {
        when (event) {
            is AuthUiEvent.ChangeUiStateEvent -> changeUiState()
            is AuthUiEvent.AuthUserEvent -> authUser(event.email, event.password)
            is AuthUiEvent.RegisterUserEvent -> registerUser(
                event.userName, event.email, event.password, event.passwordConfirm
            )
        }
    }

    private fun changeUiState() {
        when (_uiState.value) {
            is AuthUiState.SignIn -> _uiState.value = AuthUiState.SignUp()
            is AuthUiState.SignUp -> _uiState.value = AuthUiState.SignIn()
        }
    }

    private fun authUser(email: String, password: String) {
        viewModelScope.launch {
            authRepository.authUser(email, password).collectLatest { result ->
                when(result) {
                    is Response.Success -> _vmEvent.emit(AuthViewModelEvent.SuccessfulAuthEvent)
                    is Response.Error -> _vmEvent.emit(AuthViewModelEvent.ErrorAuthEvent(result.message ?: ""))
                    is Response.Loading -> {}
                }
            }
        }

    }

    private fun registerUser(userName: String, email: String, password: String, passwordConfirm: String) {
        viewModelScope.launch {
            when(val verifyResult = validateData(userName, email, password, passwordConfirm)) {
                is ValidationDataResult.Success -> sendRegisterQuery(userName, email, password)
                is ValidationDataResult.Error -> _vmEvent.emit(AuthViewModelEvent.ErrorDataVerification(verifyResult))
            }
        }
    }

    private fun validateData(userName: String, email: String, password: String, passwordConfirm: String): ValidationDataResult {
        if (userName.isEmpty())
            return ValidationDataResult.Error.UserNameIsEmpty
        if (email.isEmpty())
            return ValidationDataResult.Error.EmailIsEmpty
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() == false)
            return ValidationDataResult.Error.EmailIsInvalid
        if (password.isEmpty())
            return ValidationDataResult.Error.PasswordIsEmpty
        if (passwordConfirm.isEmpty())
            return ValidationDataResult.Error.PasswordConfirmIsEmpty
        if (password != passwordConfirm)
            return ValidationDataResult.Error.PasswordMismatch
        if (password.length < 6)
            return ValidationDataResult.Error.PasswordInvalid
        return ValidationDataResult.Success
    }

    private suspend fun sendRegisterQuery(userName: String, email: String, password: String) {
        authRepository.registerUser(userName, email, password).collectLatest { result ->
            when(result) {
                is Response.Success -> _vmEvent.emit(AuthViewModelEvent.SuccessfulAuthEvent)
                is Response.Error -> _vmEvent.emit(AuthViewModelEvent.ErrorAuthEvent(result.message ?: ""))
                is Response.Loading -> {}
            }
        }
    }

    sealed class ValidationDataResult {
        data object Success: ValidationDataResult()
        sealed class Error: ValidationDataResult() {
            data object UserNameIsEmpty: Error()
            data object EmailIsEmpty: Error()
            data object EmailIsInvalid: Error()
            data object PasswordIsEmpty: Error()
            data object PasswordConfirmIsEmpty: Error()
            data object PasswordMismatch: Error()
            data object PasswordInvalid: Error()
        }
    }
}