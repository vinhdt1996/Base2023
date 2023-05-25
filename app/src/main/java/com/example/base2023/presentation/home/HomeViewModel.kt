package com.example.base2023.presentation.home

import androidx.lifecycle.ViewModel
import com.example.base2023.base.NetworkErrorException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ShowUserMessage -> showMessage()
            is HomeEvent.ShowError -> showError()
            is HomeEvent.ShowError401 -> showError401()
            is HomeEvent.ShowError408 -> showError408()
            is HomeEvent.ClearUserMessage -> clearMessage()
            is HomeEvent.ClearError -> clearError()
        }
    }

    private fun showError() {
        _uiState.update {
            it.copy(throwable = NetworkErrorException(code = 400, errorMessage = "Error 400"))
        }
    }

    private fun showError401() {
        _uiState.update {
            it.copy(throwable = NetworkErrorException(code = 401, errorMessage = "Error 401"))
        }
    }

    private fun showError408() {
        _uiState.update {
            it.copy(throwable = NetworkErrorException(code = 408, errorMessage = "Error 408"))
        }
    }

    private fun clearError() {
        _uiState.update {
            it.copy(throwable = null)
        }
    }

    private fun showMessage() {
        _uiState.update {
            it.copy(userMessage = "Demo user message")
        }
    }

    private fun clearMessage() {
        _uiState.update {
            it.copy(userMessage = null)
        }
    }


}