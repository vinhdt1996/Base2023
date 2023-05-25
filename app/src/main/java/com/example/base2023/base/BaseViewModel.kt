package com.example.base2023.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job

abstract class BaseViewModel<ScreenState> : ViewModel() {

    abstract var baseUiState: BaseUiState<ScreenState>

    var parentJob: Job? = null
        protected set

    protected fun registerJobFinish() {
        parentJob?.invokeOnCompletion {

        }
    }

    val handler = CoroutineExceptionHandler { _, exception ->
        parseErrorCallApi(exception)
    }

    fun setRefreshing(isRefreshing: Boolean) {
        baseUiState = baseUiState.copy(
            isRefreshing = isRefreshing
        )
    }

    fun setLoading(isLoading: Boolean) {
        baseUiState = baseUiState.copy(
            isLoading = isLoading
        )
    }

    protected open fun parseErrorCallApi(e: Throwable) {
//        when (e) {
//            is NetworkErrorException -> {
//                AppEvent.hideLoading()
//                if (e.code == STATUS_CODE_UNAUTHORIZED && AppData.isAuthenticated()) {
//                    DialogUtils.showExpiredSession()
//                } else if (e.code == STATUS_CODE_NEED_LOGIN_AGAIN) {
//                    AppEvent.hideBottomSheet()
//                    DialogUtils.showError(e.errorMessage) { AppEvent.loginAgain() }
//                } else {
//                    baseUiState = baseUiState.copy(errorMessage = e.errorMessage, errors = e.errors)
//                    DialogUtils.showError(e.errorMessage) { clearErrorMessage() }
//                }
//            }
//
//            else -> {
//                AppEvent.hideLoading()
//                DialogUtils.showError(
//                    e.message
//                        ?: if (AppData.language == Constants.ENGLISH_CODE) "Unknown error" else "Lỗi không xác định"
//                )
//            }
//        }
    }

    fun firstLoaded() {
        baseUiState = baseUiState.copy(isFirstLoad = false)
    }

    fun clearErrorMessage() {
        baseUiState = baseUiState.copy(
            errorMessage = null, errors = null
        )
    }

}