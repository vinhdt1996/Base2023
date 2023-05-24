package com.example.base2023.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.base2023.presentation.common.CustomAlertDialog
import com.example.base2023.presentation.common.CustomOutlinedTextField
import com.example.base2023.presentation.common.PrimaryButton
import com.example.base2023.presentation.common.VerticalSpacer
import com.example.base2023.util.LoginTopAppBar
import com.example.base2023.vo.DialogData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit, viewModel: LoginViewModel
) {
    Scaffold(topBar = { LoginTopAppBar() }) { padding ->
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        LoginContent(
            email = uiState.email,
            password = uiState.password,
            onEvent = viewModel::onEvent,
            modifier = Modifier.padding(padding)
        )
        uiState.userMessage?.let {
            CustomAlertDialog(
                data = DialogData(textConfirm = "Close",
                    textTitle = "Error",
                    textMessage = it,
                    onConfirmClick = { viewModel.onEvent(LoginEvent.ClearMessage) })
            )
        }
        LaunchedEffect(key1 = Unit) {
            viewModel.uiEvent.collect() { event ->
                when (event) {
                    is LoginUiEvent.LoginSuccess -> onLoginSuccess()
                }
            }
        }
    }
}

@Composable
private fun LoginContent(
    email: String,
    password: String,
    onEvent: (LoginEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CustomOutlinedTextField(
            value = email,
            onValueChange = { onEvent.invoke(LoginEvent.UpdateEmail(it)) },
            placeHolder = "Email"
        )
        VerticalSpacer(height = 16f)
        CustomOutlinedTextField(
            value = password,
            onValueChange = { onEvent.invoke(LoginEvent.UpdatePassword(it)) },
            placeHolder = "Password"
        )
        VerticalSpacer(height = 16f)
        PrimaryButton(label = "Login", onClick = { onEvent.invoke(LoginEvent.Login) })
    }
}