package com.example.base2023.data.repository

import okhttp3.ResponseBody

interface DownloadRepository {

    suspend fun downloadFile(url: String): ResponseBody

}