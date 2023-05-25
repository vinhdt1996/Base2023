package com.example.base2023.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheet1(
    onMessageClick: () -> Unit,
    onErrorClick: () -> Unit,
    onError401Click: () -> Unit,
    onError408Click: () -> Unit,
    onDismissRequest: () -> Unit,
    onSnackbarClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sheet content")
        VerticalSpacer(height = 16f)
        Button(onClick = {
            onDismissRequest.invoke()
        }) {
            Text("Click to hide sheet")
        }
        VerticalSpacer(height = 16f)
        Button(onClick = {
            onMessageClick.invoke()
        }) {
            Text("Show message")
        }
        VerticalSpacer(height = 16f)
        Button(onClick = {
            onErrorClick.invoke()
        }) {
            Text("Error")
        }
        VerticalSpacer(height = 16f)
        Button(onClick = {
            onError401Click.invoke()
        }) {
            Text("401")
        }
        VerticalSpacer(height = 16f)
        Button(onClick = {
            onError408Click.invoke()
        }) {
            Text("408")
        }
        Button(onClick = {
            onSnackbarClick.invoke()
        }) {
            Text("Snackbar")
        }
        Button(onClick = {
            onLogoutClick.invoke()
        }) {
            Text("Logout")
        }
    }
}