package com.example.base2023.base


data class BaseResponse<T>(
    val message: String? = null,
    val errors: List<ErrorObject>? = null,
    val code: Int? = null,
    val data: T? = null
) {
    fun isSuccessful() = code == 200 || code == 201
}

data class ErrorObject(
    val field: String,
    val message: String,
)