package com.example.base2023.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor() : ViewModel() {

    private val _uiEvent = Channel<AppUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow().shareIn(viewModelScope, SharingStarted.Lazily)
    private suspend fun sendEvent(event: AppUiEvent) = _uiEvent.send(event)
    private fun sendEventAsync(event: AppUiEvent) = viewModelScope.launch { _uiEvent.send(event) }

    fun onEvent(event: AppEvent) {
        when (event) {
            is AppEvent.CloseDialog401 -> sendEventAsync(AppUiEvent.OnLogout)
            is AppEvent.CloseDialog408 -> sendEventAsync(AppUiEvent.OnLoginAgain)
        }
    }

}