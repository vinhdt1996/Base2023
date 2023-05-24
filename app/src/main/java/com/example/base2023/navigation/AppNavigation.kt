package com.example.base2023.navigation

import androidx.navigation.NavHostController
import com.example.base2023.navigation.AppScreens.HOME_SCREEN
import com.example.base2023.navigation.AppScreens.LOGIN_SCREEN
import com.example.base2023.navigation.AppScreens.PROFILE_SCREEN

private object AppScreens {
    const val LOGIN_SCREEN = "login"
    const val HOME_SCREEN = "home"
    const val PROFILE_SCREEN = "profile"
}

object AppDestinations {
    const val LOGIN_ROUTE = LOGIN_SCREEN
    const val HOME_ROUTE = HOME_SCREEN
    const val PROFILE_ROUTE = PROFILE_SCREEN
}

class AppNavigationActions(private val navController: NavHostController) {

    fun navigateFromLoginToHome() {
        navController.navigate(HOME_SCREEN) {
            popUpTo(0)
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToHome() {
        navController.navigate(HOME_SCREEN) {
            popUpTo(HOME_SCREEN) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToProfile() {
        navController.navigate(PROFILE_SCREEN) {
            popUpTo(HOME_SCREEN) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}