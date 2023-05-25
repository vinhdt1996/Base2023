package com.example.base2023.base

open class NetworkErrorException(
    val code: Int? = null, val errorMessage: String? = null, val errors: List<ErrorObject>? = null
) : Exception()