package com.example.base2023.presentation.login

data class LoginUiState(
    val email: String = "email",
    val password: String = "password",
    val isLoading: Boolean = false,
    val userMessage: String? = null
)
