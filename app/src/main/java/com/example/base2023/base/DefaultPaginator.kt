package com.example.base2023.base

class DefaultPaginator<T>(
    private val initialPage: Int,
    private inline val onLoading: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Int) -> BaseResponse<BasePagingResponse<T>>,
    private inline val getNextKey: suspend (BaseResponse<BasePagingResponse<T>>) -> Int,
    private inline val onSuccess: suspend (items: BaseResponse<BasePagingResponse<T>>) -> Unit
) : Paginator {

    private var currentPage: Int = initialPage
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoading(true)
        val result = onRequest(currentPage)
        isMakingRequest = false
        currentPage = getNextKey(result)
        onSuccess(result)
        onLoading(false)
    }

    override fun reset() {
        currentPage = initialPage
    }
}