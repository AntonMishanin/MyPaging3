package com.my.mypaging3.custom.domain

import android.util.Log
import com.my.mypaging3.library.data.local.LocalUserRepository

class CustomPagingInteractor(
    private val repository: LocalUserRepository
) {
    private var isPagingAvailable = true

    //TODO: Create wrapper - PagerConfig
    private var prefetchDistance = 2
    private var maxSize = 20
    private var currentPageSize = 0
    private var jumpThreshold = 5

    suspend fun fetchUsersByPage(page: Int): PagingState {

        if (!isPagingAvailable) {
            return PagingState.Loading
        }

        if (page + prefetchDistance >= currentPageSize) {
            currentPageSize = page + jumpThreshold
            isPagingAvailable = false
        } else {
            return PagingState.SkipRequest
        }

        if (currentPageSize >= maxSize) {
            currentPageSize = maxSize
        }

        Log.d("EE", "REQUEST")
        val users = repository.fetchUsersByPage(currentPageSize)
        Log.d("EE", "SUCCESS")
        isPagingAvailable = users.size == currentPageSize

        return PagingState.Content(users)
    }
}