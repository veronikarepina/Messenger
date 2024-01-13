package com.example.messengerapplication.presentation.start_and_auth.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.messengerapplication.R
import com.example.messengerapplication.presentation.start_and_auth.screens.splash.viewmodel.SplashViewModel
import com.example.messengerapplication.presentation.start_and_auth.screens.splash.viewmodel.SplashViewModelEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    navController: NavController,
    startMainActivity: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = null) {
        viewModel.vmEvent.collectLatest {
            when (it) {
                is SplashViewModelEvent.NavigationToAuthEvent -> navigate(it.route, navController)
                is SplashViewModelEvent.NavigationToMainEvent -> startMainActivity.invoke()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.main_color)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.messenger),
            color = colorResource(id = R.color.black),
            fontSize = 30.sp
        )
    }
}

private fun navigate(route: String, navController: NavController) {
    navController.navigate(route)
}