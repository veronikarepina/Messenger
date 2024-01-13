package com.example.messengerapplication.presentation.start_and_auth.screens.auth.viewModel

import com.example.messengerapplication.base.BaseViewModelEvent

sealed class AuthViewModelEvent: BaseViewModelEvent {

    data object SuccessfulAuthEvent: AuthViewModelEvent()
    data class ErrorDataVerification(val errorType: AuthViewModel.ValidationDataResult.Error): AuthViewModelEvent()
    data class ErrorAuthEvent(val message: String) : AuthViewModelEvent()
}