package com.example.base2023.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    scaffoldState: BottomSheetScaffoldState,
    onDismissRequest: () -> Unit,
    sheetContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    BottomSheetScaffold(sheetPeekHeight = 0.dp, scaffoldState = scaffoldState, sheetContent = {
        sheetContent()
    }) {
        content()
        val isVisible = remember(scaffoldState.bottomSheetState.targetValue) {
            mutableStateOf(scaffoldState.bottomSheetState.targetValue == SheetValue.Expanded)
        }
        if (isVisible.value) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
                .clickableNoEffect { onDismissRequest.invoke() })
        }
    }
}