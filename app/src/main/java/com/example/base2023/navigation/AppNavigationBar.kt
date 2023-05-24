package com.example.base2023.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.base2023.ui.theme.CustomTheme
import com.example.base2023.ui.theme.GrayAC
import com.example.base2023.ui.theme.WhiteFC
import com.example.base2023.ui.theme.WhiteFC40

@Composable
fun AppNavigationBar(
    currentDestination: NavDestination?,
    tabItems: List<BottomBarItem>,
    onBottomBarItemClick: (BottomBarItem) -> Unit
) {
    val selectionMap = remember(currentDestination) {
        tabItems.associateWith { tabItem ->
            (currentDestination?.hierarchy?.any { it.route == tabItem.route } == true)
        }
    }

    NavigationBar() {
        tabItems.forEach { tabItem ->
            val selected = selectionMap.getOrDefault(tabItem, false)
            NavigationBarItem(selected = selected,
                onClick = { onBottomBarItemClick.invoke(tabItem) },
                icon = {
                    Icon(
                        modifier = Modifier.padding(bottom = 4.dp),
                        painter = painterResource(id = tabItem.iconId),
                        contentDescription = null,
                        tint = if (selected) WhiteFC else if (CustomTheme.colors.darkTheme) WhiteFC40 else GrayAC
                    )
                },
                label = {
                    Text(
                        text = stringResource(tabItem.stringId),
                        style = CustomTheme.typography.medium10,
                    )
                })
        }
    }

}