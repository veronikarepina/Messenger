package com.example.messengerapplication.presentation.main.main_screen.state

import com.example.messengerapplication.base.BaseUiState
import com.example.messengerapplication.presentation.main.MainBottomScreens
import com.example.messengerapplication.presentation.main.getNavigationList

data class MainUiState(
    var selectedItemRoute: String = MainBottomScreens.Chats.route,
    val bottomNavigationList: List<MainBottomScreens> = getNavigationList(),
) : BaseUiState