package com.example.base2023.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.base2023.R

sealed class BottomBarItem(
    open val route: String, @DrawableRes open val iconId: Int, @StringRes open val stringId: Int
) {
    class Home(
        override val route: String = AppDestinations.HOME_ROUTE,
        override val iconId: Int = R.drawable.ic_home,
        override val stringId: Int = R.string.home
    ) : BottomBarItem(route, iconId, stringId)

    class Profile(
        override val route: String = AppDestinations.PROFILE_ROUTE,
        override val iconId: Int = R.drawable.ic_profile,
        override val stringId: Int = R.string.profile
    ) : BottomBarItem(route, iconId, stringId)
}