package com.example.messengerapplication.presentation.main.main_screen.event

import com.example.messengerapplication.base.BaseUiEvent

sealed class MainUiEvent: BaseUiEvent {

    data class ChangeRoute(val route: String): MainUiEvent()
}