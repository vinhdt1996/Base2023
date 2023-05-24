package com.example.base2023.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()
    private val _uiEvent = Channel<LoginUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow().shareIn(viewModelScope, SharingStarted.Lazily)
    private suspend fun sendEvent(event: LoginUiEvent) = _uiEvent.send(event)
    private fun sendEventAsync(event: LoginUiEvent) = viewModelScope.launch { _uiEvent.send(event) }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> validateUserInput()
            is LoginEvent.UpdateEmail -> updateEmail(event.email)
            is LoginEvent.UpdatePassword -> updatePassword(event.password)
            is LoginEvent.ClearMessage -> clearMessage()
        }
    }

    private fun updateEmail(newEmail: String) {
        _uiState.update {
            it.copy(email = newEmail)
        }
    }

    private fun updatePassword(newPassword: String) {
        _uiState.update {
            it.copy(password = newPassword)
        }
    }

    private fun validateUserInput() {
        if (uiState.value.email.isBlank() || uiState.value.password.isBlank()) {
            _uiState.update {
                it.copy(userMessage = "Thông tin đăng nhập không được bỏ trống")
            }
            return
        }
        login()
    }

    private fun clearMessage() {
        _uiState.update {
            it.copy(userMessage = null)
        }
    }

    private fun login() {
        sendEventAsync(LoginUiEvent.LoginSuccess)
    }
}