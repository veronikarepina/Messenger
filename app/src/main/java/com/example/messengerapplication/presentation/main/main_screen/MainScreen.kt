package com.example.messengerapplication.presentation.main.main_screen

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.messengerapplication.R
import com.example.messengerapplication.presentation.main.MainNavGraph
import com.example.messengerapplication.presentation.main.main_screen.event.MainUiEvent
import com.example.messengerapplication.presentation.main.main_screen.viewmodel.MainViewModel
import com.example.messengerapplication.presentation.main.main_screen.viewmodel.MainViewModelEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val navController: NavHostController = rememberNavController()

    LaunchedEffect(key1 = null) {
        viewModel.vmEvent.collectLatest {
            when(it) {
                is MainViewModelEvent.ChangeRoute -> navigate(navController, it.route)
            }
        }
    }


    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(90.dp),
                containerColor = colorResource(id = R.color.white)
            ) {
                uiState.bottomNavigationList.forEach { item ->
                    BottomNavigationItem(
                        selected = item.isSelected,
                        onClick = {
                            viewModel.postUiEvent(MainUiEvent.ChangeRoute(item.route))
                        },
                        label = {
                            Text(
                                text = stringResource(id = item.title),
                                fontSize = 14.sp,
                                color = colorResource(id = R.color.black)
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = null,
                                tint = if (uiState.selectedItemRoute == item.route)
                                    colorResource(id = R.color.main_color)
                                else colorResource(id = R.color.black)
                            )
                        }
                    )
                }
            }
        }
    ) {
        MainNavGraph(navHostController = navController, modifier = Modifier.padding(it))
    }
}

private fun navigate(navHostController: NavHostController, route: String) {
    navHostController.navigate(route)
}