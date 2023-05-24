package com.example.base2023.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.base2023.util.clickableNoEffect

@Composable
fun LoadingView() {
    Box(modifier = Modifier
        .fillMaxSize()
        .clickableNoEffect { }
        .background(Color.Black.copy(alpha = 0.6f)), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}