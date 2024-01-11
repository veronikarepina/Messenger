package com.example.messengerapplication.presentation.main.main_screen.viewmodel

import com.example.messengerapplication.base.BaseViewModelEvent

sealed class MainViewModelEvent: BaseViewModelEvent {

    data class ChangeRoute(val route: String): MainViewModelEvent()
}