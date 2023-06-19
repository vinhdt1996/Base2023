package com.example.base2023.presentation.login

import java.io.InputStream

sealed interface LoginEvent {
    object Login : LoginEvent
    object Download : LoginEvent
    class UpdateEmail(val email: String) : LoginEvent
    class UpdatePassword(val password: String) : LoginEvent
    object ClearMessage : LoginEvent
}

sealed interface LoginUiEvent {
    object LoginSuccess : LoginUiEvent
    class Downloaded(val stream: InputStream) : LoginUiEvent
}