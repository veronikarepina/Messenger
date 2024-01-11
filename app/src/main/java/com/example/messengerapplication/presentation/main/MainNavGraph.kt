package com.example.messengerapplication.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.messengerapplication.presentation.main.inner_screens.chats.ChatsScreen
import com.example.messengerapplication.presentation.main.inner_screens.profile.ProfileScreen

@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = MainBottomScreens.Chats.route,
        modifier = modifier
    ) {
        composable(route = MainBottomScreens.Chats.route) {
            ChatsScreen()
        }

        composable(route = MainBottomScreens.Profile.route) {
            ProfileScreen()
        }
    }
}