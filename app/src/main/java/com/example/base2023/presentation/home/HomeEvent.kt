package com.example.base2023.presentation.home

sealed interface HomeEvent {
    object ShowUserMessage : HomeEvent
    object ShowError : HomeEvent
    object ShowError401 : HomeEvent
    object ShowError408 : HomeEvent
    object ClearUserMessage : HomeEvent
    object ClearError : HomeEvent
}