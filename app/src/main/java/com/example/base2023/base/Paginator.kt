package com.example.base2023.base

interface Paginator {
    suspend fun loadNextItems()
    fun reset()
}