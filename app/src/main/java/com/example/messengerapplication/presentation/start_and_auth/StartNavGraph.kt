package com.example.messengerapplication.presentation.start_and_auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.messengerapplication.presentation.start_and_auth.screens.auth.AuthScreen
import com.example.messengerapplication.presentation.start_and_auth.screens.splash.SplashScreen

@Composable
fun StartNavGraph(
    navHostController: NavHostController,
    startMainActivity: () -> Unit
) {
    NavHost(navController = navHostController, startDestination = StartScreens.Splash.route) {
        composable(route = StartScreens.Splash.route) {
            SplashScreen(navController = navHostController)
        }
        composable(route = StartScreens.Auth.route) {
            AuthScreen(startMainActivity = { startMainActivity.invoke() })
        }
    }
}