package com.example.messengerapplication.presentation.start_and_auth.screens.auth.event

import com.example.messengerapplication.base.BaseUiEvent

sealed class AuthUiEvent: BaseUiEvent {

    data object ChangeUiStateEvent: AuthUiEvent()

    data class AuthUserEvent(val email: String, val password: String): AuthUiEvent()

    data class RegisterUserEvent(
        val userName: String,
        val email: String,
        val password: String,
        val passwordConfirm: String
    ): AuthUiEvent()
}