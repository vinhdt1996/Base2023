package com.example.base2023.data.repository.impl

import com.example.base2023.base.NetworkResult
import com.example.base2023.data.network.source.DownloadRemoteDataSource
import com.example.base2023.data.repository.DownloadRepository
import com.example.base2023.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

class DownloadRepositoryImpl @Inject constructor(
    private val dataSource: DownloadRemoteDataSource,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : DownloadRepository {

    override suspend fun downloadFile(url: String): ResponseBody =
        withContext(dispatcher) {
            when (val result = dataSource.downloadFile(url)) {
                is NetworkResult.Success -> {
                    result.data
                }

                is NetworkResult.Error -> {
                    throw result.exception
                }
            }
        }

}