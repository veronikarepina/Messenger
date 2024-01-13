package com.example.messengerapplication.presentation.start_and_auth.screens.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messengerapplication.presentation.start_and_auth.StartScreens
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val firebaseUser: FirebaseUser?
): ViewModel() {

    private val _vmEvent: MutableSharedFlow<SplashViewModelEvent> = MutableSharedFlow()
    val vmEvent: SharedFlow<SplashViewModelEvent> = _vmEvent.asSharedFlow()

    init {
        tryLogin()
    }

    private fun tryLogin() {
        viewModelScope.launch {
            delay(1000L)
            if (firebaseUser != null)
                _vmEvent.emit(SplashViewModelEvent.NavigationToMainEvent)
            else
                _vmEvent.emit(SplashViewModelEvent.NavigationToAuthEvent(route = StartScreens.Auth.route))
        }
    }
}