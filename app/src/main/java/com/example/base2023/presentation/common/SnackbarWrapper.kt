package com.example.base2023.presentation.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.base2023.R
import com.example.base2023.vo.ToastData

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SnackbarWrapper(
    snackbarHostState: SnackbarHostState, content: @Composable () -> Unit
) {
    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState,
            modifier = Modifier.padding(bottom = 100.dp),
            snackbar = {
                AppSnackbar(
                    ToastData(
                        iconId = R.drawable.ic_check_circle,
                        message = snackbarHostState.currentSnackbarData?.visuals?.message.toString()
                    )
                )
            })
    }) {
        content()
    }
}