package com.example.messengerapplication.presentation.start_and_auth.screens.auth.state

import com.example.messengerapplication.base.BaseUiState

sealed class AuthUiState: BaseUiState {

    data class SignIn(
        val isLoading: Boolean = false,
        val isSuccess: String = "",
        val isError: String = ""
    ): AuthUiState()

    data class SignUp(
        val isLoading: Boolean = false,
        val isSuccess: String = "",
        val isError: String = ""
    ): AuthUiState()
}