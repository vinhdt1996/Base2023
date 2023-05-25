package com.example.base2023.navigation

sealed interface AppEvent {

    object CloseDialog401 : AppEvent

    object CloseDialog408 : AppEvent

}

sealed interface AppUiEvent {
    object OnLogout : AppUiEvent

    object OnLoginAgain : AppUiEvent
}