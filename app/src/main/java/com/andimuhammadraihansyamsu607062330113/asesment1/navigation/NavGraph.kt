package com.andimuhammadraihansyamsu607062330113.asesment1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andimuhammadraihansyamsu607062330113.asesment1.ui.screen.MainScreen
import com.andimuhammadraihansyamsu607062330113.asesment1.ui.screen.AppInfoAndTipsScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable  (route = Screen.AppInfoAndTips.route) {
            AppInfoAndTipsScreen(navController)
        }
    }
}