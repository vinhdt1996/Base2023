//package com.example.base2023.base
//
//import com.example.base2023.base.NetworkErrorException
//import com.example.base2023.base.NetworkResult
//import com.example.uniinternal.util.AppUtils
//import com.google.gson.Gson
//import retrofit2.Response
//
//open class DemoBaseRemoteDataSource {
//
//    protected suspend fun <T : Any> callApi(call: suspend () -> Response<T>): NetworkResult<T> {
//        if (!AppUtils.isInternetAvailable()) {
//            return NetworkResult.Error(NetworkErrorException(null, "No internet connection!"))
//        }
//        val response: Response<T>
//        try {
//            response = call.invoke()
//        } catch (t: Throwable) {
//            return NetworkResult.Error(NetworkErrorException(null, t.message))
//        }
//
//
//        return if (response.isSuccessful) {
//            if (response.body() == null) {
//                NetworkResult.Error(
//                    NetworkErrorException(
//                        200, "Response without body"
//                    )
//                )
//            } else {
//                NetworkResult.Success(response.body()!!)
//            }
//        } else {
//            response.errorBody()?.string()?.let { body ->
//                val baseResponse = Gson().fromJson(body, BaseResponse::class.java)
//                return NetworkResult.Error(
//                    NetworkErrorException(
//                        baseResponse.code, baseResponse.message, baseResponse.errors
//                    )
//                )
//            }
//            NetworkResult.Error(NetworkErrorException(response.code(), response.message()))
//        }
//    }
//
//}