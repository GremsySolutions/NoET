package com.example.noet.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.noet.R

sealed class BottomNavItem (
    val route: String,
    val label: String,
    @DrawableRes val icon: Int
) {
    data object Home: BottomNavItem(
        route = Screen.Home.route,
        label = "Home",
        icon = R.drawable.ic_home
    )

    data object Category: BottomNavItem(
        route = Screen.Category.route,
        label = "Category",
        icon = R.drawable.ic_category
    )

    data object Paragraph: BottomNavItem(
        route = Screen.Paragraph.route,
        label = "Paragraph",
        icon = R.drawable.ic_scan
    )

    data object Test: BottomNavItem(
        route = Screen.Test.route,
        label = "Test",
        icon = R.drawable.ic_test
    )
}