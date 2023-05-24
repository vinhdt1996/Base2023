package com.example.base2023.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.base2023.presentation.home.HomeScreen
import com.example.base2023.presentation.login.LoginScreen
import com.example.base2023.presentation.login.LoginViewModel
import com.example.base2023.presentation.profile.ProfileScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestinations.LOGIN_ROUTE,
    navActions: AppNavigationActions = remember(navController) {
        AppNavigationActions(navController)
    },
    tabItems: List<BottomBarItem> = remember {
        listOf(BottomBarItem.Home(), BottomBarItem.Profile())
    }
) {

    val onBottomBarItemClick: (BottomBarItem) -> Unit = {
        when (it) {
            is BottomBarItem.Home -> navActions.navigateToHome()
            is BottomBarItem.Profile -> navActions.navigateToProfile()
        }
    }

    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable(AppDestinations.LOGIN_ROUTE) {
            val viewModel = hiltViewModel<LoginViewModel>()
            // Toast
            // BottomSheet
            LoginScreen(
                onLoginSuccess = { navActions.navigateFromLoginToHome() }, viewModel = viewModel
            )
        }

        composable(AppDestinations.HOME_ROUTE) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            HomeScreen(
                currentDestination = currentDestination,
                tabItems = tabItems,
                onBottomBarItemClick = onBottomBarItemClick
            )
        }

        composable(AppDestinations.PROFILE_ROUTE) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            ProfileScreen(
                currentDestination = currentDestination,
                tabItems = tabItems,
                onBottomBarItemClick = onBottomBarItemClick
            )
        }
    }

}