package com.example.base2023.presentation.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RowScope.FillWidthSpacer() {
    Spacer(modifier = Modifier.weight(1f))
}