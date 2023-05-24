package com.example.base2023.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DialogData(
    val textConfirm: String,
    val textDismiss: String? = null,
    val textTitle: String,
    val textMessage: String? = null,
    val messageToBold: String? = null,
    val onConfirmClick: (() -> Unit),
    val onDismissClick: (() -> Unit)? = null,
    val onDismissRequest: (() -> Unit)? = null,
) : Parcelable