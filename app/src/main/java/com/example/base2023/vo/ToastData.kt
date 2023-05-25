package com.example.base2023.vo

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class ToastData(@DrawableRes val iconId: Int? = null, val message: String) : Parcelable