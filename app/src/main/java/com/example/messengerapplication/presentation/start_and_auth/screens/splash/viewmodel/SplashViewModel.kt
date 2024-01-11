package com.example.messengerapplication.presentation.start_and_auth.screens.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messengerapplication.presentation.start_and_auth.StartScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): ViewModel() {

    private val _vmEvent: MutableSharedFlow<SplashViewModelEvent> = MutableSharedFlow()
    val vmEvent: SharedFlow<SplashViewModelEvent> = _vmEvent.asSharedFlow()

    init {
        tryLogin()
    }

    private fun tryLogin() {
        viewModelScope.launch {
            delay(1000L)
            val route = StartScreens.Auth.route
            _vmEvent.emit(SplashViewModelEvent.NavigationEvent(route = route))
        }
    }
}