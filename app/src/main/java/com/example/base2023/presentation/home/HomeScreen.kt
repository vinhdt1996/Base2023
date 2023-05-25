package com.example.base2023.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import com.example.base2023.navigation.AppNavigationBar
import com.example.base2023.navigation.BottomBarItem
import com.example.base2023.presentation.common.BottomSheet1
import com.example.base2023.presentation.common.CustomAlertDialog
import com.example.base2023.vo.DialogData

@Composable
fun HomeScreen(
    currentDestination: NavDestination?,
    tabItems: List<BottomBarItem>,
    onBottomBarItemClick: (BottomBarItem) -> Unit,
    onBottomSheetClick: (@Composable () -> Unit) -> Unit,
    onSnackbarClick: (String) -> Unit,
    onDismissBottomSheet: () -> Unit,
    onLogoutClick: () -> Unit,
    viewModel: HomeViewModel
) {
    Scaffold(bottomBar = {
        AppNavigationBar(
            currentDestination = currentDestination,
            tabItems = tabItems,
            onBottomBarItemClick = onBottomBarItemClick
        )
    }) { padding ->
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        HomeContent(modifier = Modifier.padding(padding), onBottomSheetClick = {
            onBottomSheetClick.invoke {
                BottomSheet1(
                    onMessageClick = {
                        viewModel.onEvent(HomeEvent.ShowUserMessage)
                        onDismissBottomSheet.invoke()
                    },
                    onErrorClick = {
                        viewModel.onEvent(HomeEvent.ShowError)
                        onDismissBottomSheet.invoke()
                    },
                    onError401Click = {
                        viewModel.onEvent(HomeEvent.ShowError401)
                        onDismissBottomSheet.invoke()
                    },
                    onError408Click = {
                        viewModel.onEvent(HomeEvent.ShowError408)
                        onDismissBottomSheet.invoke()
                    },
                    onDismissRequest = onDismissBottomSheet,
                    onSnackbarClick = { onSnackbarClick.invoke("Snackbar message") },
                    onLogoutClick = onLogoutClick
                )
            }
        })

        uiState.userMessage?.let {
            CustomAlertDialog(
                data = DialogData(textConfirm = "Close",
                    textTitle = "Error",
                    textMessage = it,
                    onConfirmClick = { viewModel.onEvent(HomeEvent.ClearUserMessage) })
            )
        }

        DisposableEffect(key1 = Unit) {
            onDispose {
                onDismissBottomSheet.invoke()
            }
        }
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier, onBottomSheetClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Home Screen")
        Button(onClick = onBottomSheetClick) {
            Text(text = "Bottom Sheet")
        }
    }
}