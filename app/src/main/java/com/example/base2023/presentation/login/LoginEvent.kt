package com.example.base2023.presentation.login

sealed interface LoginEvent {
    object Login : LoginEvent
    class UpdateEmail(val email: String) : LoginEvent
    class UpdatePassword(val password: String) : LoginEvent
    object ClearMessage : LoginEvent
}

sealed interface LoginUiEvent {
    object LoginSuccess : LoginUiEvent
}