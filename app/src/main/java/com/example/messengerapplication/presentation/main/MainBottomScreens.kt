package com.example.messengerapplication.presentation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.messengerapplication.R

sealed class MainBottomScreens(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    var isSelected: Boolean
) {

    data object Chats: MainBottomScreens("chats_screen", R.string.chats, R.drawable.ic_chat, true)

    data object Profile: MainBottomScreens("portfolio_screen", R.string.profile, R.drawable.ic_account, false)
}

fun getNavigationList(): List<MainBottomScreens> {
    return listOf(
        MainBottomScreens.Chats,
        MainBottomScreens.Profile
    )
}