package com.example.messengerapplication.presentation.start_and_auth

sealed class StartScreens(val route: String) {

    data object Splash: StartScreens(route = "splash_screen")

    data object Auth: StartScreens(route = "auth_screen")
}