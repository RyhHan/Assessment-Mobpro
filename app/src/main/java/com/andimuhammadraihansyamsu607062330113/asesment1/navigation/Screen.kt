package com.andimuhammadraihansyamsu607062330113.asesment1.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("mainScreen")
    data object AppInfoAndTips : Screen("appInfoAndTips")
}