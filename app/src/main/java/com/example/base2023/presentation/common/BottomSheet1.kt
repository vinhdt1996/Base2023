package com.example.base2023.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheet1(onDismissRequest: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(128.dp), contentAlignment = Alignment.Center
    ) {
        Text("Swipe up to expand sheet")
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sheet content")
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            onDismissRequest.invoke()
        }) {
            Text("Click to hide sheet")
        }
    }
}