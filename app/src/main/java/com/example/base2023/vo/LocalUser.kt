package com.example.base2023.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalUser(
    var userId: String = "",
    var userName: String = "",
    var userPinCode: String = "",
    var accessToken: String = "",
    var refreshToken: String = "",
    var imgUrl: String = "",
) : Parcelable
