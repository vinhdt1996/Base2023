package com.example.base2023.base

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error<out T : Any>(val exception: NetworkErrorException, val data: T? = null) :
        NetworkResult<T>()
}
