//package com.example.base2023.base
//
//import com.example.base2023.base.DemoBasePagingResponse
//import com.example.base2023.base.Paginator
//
//class DemoDefaultPaginator<T>(
//    private val initialPage: Int,
//    private inline val onLoading: (Boolean) -> Unit,
//    private inline val onRequest: suspend (nextKey: Int) -> DemoBasePagingResponse<T>,
//    private inline val getNextKey: suspend (DemoBasePagingResponse<T>) -> Int,
//    private inline val onSuccess: suspend (items: DemoBasePagingResponse<T>, newKey: Int) -> Unit
//) : Paginator {
//
//    private var currentPage: Int = initialPage
//    private var isMakingRequest = false
//
//    override suspend fun loadNextItems() {
//        if (isMakingRequest) {
//            return
//        }
//        isMakingRequest = true
//        onLoading(true)
//        val result = onRequest(currentPage)
//        isMakingRequest = false
//        currentPage = getNextKey(result)
//        onSuccess(result, currentPage)
//        onLoading(false)
//    }
//
//    override fun reset() {
//        currentPage = initialPage
//    }
//}