package com.example.messengerapplication.presentation.start_and_auth.screens.auth.state

import com.example.messengerapplication.base.BaseUiState

sealed class AuthUiState: BaseUiState {

    data object SignIn: AuthUiState()

    data object SignUp: AuthUiState()
}