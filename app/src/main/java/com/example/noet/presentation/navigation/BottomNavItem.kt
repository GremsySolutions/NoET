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
        label = "Khám phá",
        icon = R.drawable.ic_home
    )

    data object Category: BottomNavItem(
        route = Screen.Category.route,
        label = "Danh mục",
        icon = R.drawable.ic_category
    )

    data object Paragraph: BottomNavItem(
        route = Screen.Paragraph.route,
        label = "Đoạn văn",
        icon = R.drawable.ic_scan
    )

    data object Test: BottomNavItem(
        route = Screen.Test.route,
        label = "Kiểm tra",
        icon = R.drawable.ic_test
    )
}