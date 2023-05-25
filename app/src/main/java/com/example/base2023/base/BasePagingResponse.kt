package com.example.base2023.base

data class BasePagingResponse<T>(
    val data: List<T>, val paginate: Paginate
)

data class Paginate(
    val limit: Int = 0, val page: Int = 0, val totalRows: Int = 0
) {
    fun isEndReached(): Boolean = page * limit >= totalRows
}