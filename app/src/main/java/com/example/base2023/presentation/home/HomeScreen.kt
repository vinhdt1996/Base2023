package com.example.base2023.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.example.base2023.navigation.AppNavigationBar
import com.example.base2023.navigation.BottomBarItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    currentDestination: NavDestination?,
    tabItems: List<BottomBarItem>,
    onBottomBarItemClick: (BottomBarItem) -> Unit
) {
    Scaffold(bottomBar = {
        AppNavigationBar(
            currentDestination = currentDestination,
            tabItems = tabItems,
            onBottomBarItemClick = onBottomBarItemClick
        )
    }) { padding ->
        HomeContent(
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Home Screen")
    }
}