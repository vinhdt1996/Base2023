package com.example.base2023.base

import com.example.base2023.data.network.api.ApiService
import retrofit2.Response

open class BaseRemoteDataSource(private val api: ApiService) {

    protected suspend fun <T : Any> callApi(call: suspend () -> Response<T>): NetworkResult<T> {
//        if (!AppUtils.isInternetAvailable()) {
//            return NetworkResult.Error(NetworkErrorException(null, "No internet connection!"))
//        }
        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            return NetworkResult.Error(NetworkErrorException(null, t.message))
        }

        if (response.isSuccessful) {
            return if (response.body() == null) {
                NetworkResult.Error(
                    NetworkErrorException(
                        200, "Response without body"
                    )
                )
            } else {
                NetworkResult.Success(response.body()!!)
            }
        } else {
//            val errorBody = response.errorBody()?.string()
//            if (errorBody != null) {
//                val baseResponse = Gson().fromJson(errorBody, BaseResponse::class.java)
//                return if (baseResponse.code == STATUS_CODE_UNAUTHORIZED && AppData.isAuthenticated()) {
//                    callApiRefreshToken(originCall = call)
//                } else {
//                    NetworkResult.Error(
//                        NetworkErrorException(
//                            baseResponse.code, baseResponse.message, baseResponse.errors
//                        ),
//                        baseResponse as T // T MUST BE BaseResponse
//                    )
//                }
//            } else {
            return NetworkResult.Error(
                NetworkErrorException(
                    response.code(), response.message()
                )
            )
        }
    }
}

//    private suspend fun <T : Any> callApiRefreshToken(originCall: suspend () -> Response<T>): NetworkResult<T> {
//        if (!AppUtils.isInternetAvailable()) {
//            return NetworkResult.Error(
//                NetworkErrorException(
//                    null,
//                    "Refresh token no internet connection!"
//                )
//            )
//        }
//
//        val response: Response<BaseResponse<TokenApiModel>>
//        try {
//            AppData.currentUser?.apply {
//                accessToken = refreshToken
//            }
//            response = uniInternalApi.refreshToken()
//        } catch (t: Throwable) {
//            return NetworkResult.Error(NetworkErrorException(null, t.message))
//        }
//
//        if (response.isSuccessful) {
//            return if (response.body() != null) {
//                val apiToken = response.body()!!.data
//                AppData.currentUser?.apply {
//                    accessToken = apiToken?.accessToken!!
//                    refreshToken = apiToken.refreshToken!!
//                }
//                callApi(originCall)
//            } else {
//                NetworkResult.Error(
//                    NetworkErrorException(
//                        200, "Refresh token without body"
//                    )
//                )
//            }
//        } else {
//            val errorBody = response.errorBody()?.string()
//            return if (errorBody != null) {
//                val baseResponse = Gson().fromJson(errorBody, BaseResponse::class.java)
//                NetworkResult.Error(
//                    NetworkErrorException(
//                        baseResponse.code, baseResponse.message, baseResponse.errors
//                    )
//                )
//            } else {
//                NetworkResult.Error(
//                    NetworkErrorException(
//                        response.code(), response.message()
//                    )
//                )
//            }
//        }
//    }
//}