package com.example.base2023.presentation.common

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.FillHeightSpacer() {
    Spacer(modifier = Modifier.weight(1f))
}