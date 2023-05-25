package com.example.base2023.base


data class BaseUiState<ScreenState>(
    val isFirstLoad: Boolean = true,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null,
    val errors: List<ErrorObject>? = null,
    var screenState: ScreenState
)
