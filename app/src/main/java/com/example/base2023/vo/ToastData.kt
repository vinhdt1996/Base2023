package com.example.base2023.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ToastData(val iconId: Int? = null, val message: String) : Parcelable