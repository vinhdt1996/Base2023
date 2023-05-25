package com.example.base2023.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.base2023.base.NetworkErrorException
import com.example.base2023.util.Constants.STATUS_CODE_NEED_LOGIN_AGAIN
import com.example.base2023.util.Constants.STATUS_CODE_UNAUTHORIZED
import com.example.base2023.vo.DialogData

@Composable
fun ErrorHandlerWrapper(
    throwable: Throwable?,
    onCloseErrorDialog401: () -> Unit,
    onCloseErrorDialog408: () -> Unit,
    onCloseErrorDialog: () -> Unit,
    content: @Composable () -> Unit
) {
    content()
    throwable?.let {
        var message by remember { mutableStateOf<String?>(null) }
        var onClose by remember { mutableStateOf<(() -> Unit)?>(null) }

        when (throwable) {
            is NetworkErrorException -> {
                if (throwable.code == STATUS_CODE_UNAUTHORIZED
//                    && AppData.isAuthenticated() todo
                ) {
                    message = "401"
                    onClose = onCloseErrorDialog401
                } else if (throwable.code == STATUS_CODE_NEED_LOGIN_AGAIN) {
                    message = "408"
                    onClose = onCloseErrorDialog408
                } else {
                    message = "Error"
                    onClose = onCloseErrorDialog
                }
            }

            else -> {
                message = "Unknown Error"
                onClose = onCloseErrorDialog

            }
        }
        CustomAlertDialog(
            data = DialogData(textConfirm = "Close",
                textTitle = "Error",
                textMessage = message,
                onConfirmClick = onClose ?: {})
        )
    }
}