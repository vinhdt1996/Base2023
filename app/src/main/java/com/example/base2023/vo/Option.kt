package com.example.base2023.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Option(
    val id: Int,
    val label: String,
    val selected: Boolean,
) : Parcelable