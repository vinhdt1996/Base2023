package com.example.base2023.presentation.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.base2023.ui.theme.CustomTheme
import com.example.base2023.vo.DialogData

@Composable
fun CustomAlertDialog(
    data: DialogData
) {
    @Suppress("UNUSED_EXPRESSION")
    AlertDialog(containerColor = CustomTheme.colors.backgroundBottomSheet,
        onDismissRequest = { data.onDismissRequest?.invoke() },
        confirmButton = {
            TextButton(onClick = {
                data.onConfirmClick.invoke()
            }) {
                Text(
                    text = data.textConfirm,
                    style = CustomTheme.typography.clickable.copy(fontSize = 17.sp)
                )
            }
        },
        dismissButton = {
            if (data.textDismiss == null) null else (TextButton(onClick = {
                data.onDismissClick?.invoke()
            }) {
                Text(
                    text = data.textDismiss,
                    style = CustomTheme.typography.clickable.copy(fontSize = 17.sp)
                )
            })
        },
        title = {
            Text(
                text = data.textTitle, style = CustomTheme.typography.semiBold17
            )
        },
        text = {
            if (data.textMessage == null) null else {
                if (data.messageToBold == null) {
                    Text(
                        text = data.textMessage, style = CustomTheme.typography.regular14
                    )
                } else {
                    val start = data.textMessage.indexOf(data.messageToBold)
                    val spanStyles = listOf(
                        AnnotatedString.Range(
                            SpanStyle(fontWeight = FontWeight.Bold),
                            start = start,
                            end = start + data.messageToBold.length
                        )
                    )
                    Text(
                        text = AnnotatedString(
                            text = data.textMessage, spanStyles = spanStyles
                        ), style = CustomTheme.typography.regular14
                    )
                }

            }
        })
}