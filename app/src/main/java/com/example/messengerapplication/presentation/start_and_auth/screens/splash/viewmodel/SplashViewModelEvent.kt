package com.example.messengerapplication.presentation.start_and_auth.screens.splash.viewmodel

import com.example.messengerapplication.base.BaseViewModelEvent

sealed class SplashViewModelEvent: BaseViewModelEvent {

    class NavigationEvent(val route: String): SplashViewModelEvent()
}