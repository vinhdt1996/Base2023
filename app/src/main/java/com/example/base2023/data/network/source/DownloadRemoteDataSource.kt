package com.example.base2023.data.network.source

import com.example.base2023.base.BaseRemoteDataSource
import com.example.base2023.base.NetworkResult
import com.example.base2023.data.network.api.ApiService
import okhttp3.ResponseBody
import javax.inject.Inject

class DownloadRemoteDataSource @Inject constructor(private val api: ApiService) :
    BaseRemoteDataSource(api) {

    suspend fun downloadFile(url: String): NetworkResult<ResponseBody> = callApi {
        api.downloadFile(url)
    }

}