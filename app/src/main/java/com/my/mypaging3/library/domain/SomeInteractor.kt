package com.my.mypaging3.library.domain

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class SomeInteractor(
    private val localUserRepository: UserRepository
) : PagingSource<Int, User>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, User> {
        Log.d("EE", "override suspend fun load")
        try {
            val nextPageNumber = params.key ?: 1
            val users = localUserRepository.fetchUsersByPage(nextPageNumber)

            if (users.size < nextPageNumber) {
                //TODO: fetch users from remote data source, save to local and return result
                return LoadResult.Page(
                    data = users,
                    prevKey = null, // Only paging forward.
                    nextKey = nextPageNumber + 1
                )
            } else {
                return LoadResult.Page(
                    data = users,
                    prevKey = null, // Only paging forward.
                    nextKey = nextPageNumber + 1
                )
            }
        } catch (e: Exception) {
            //TODO: Handle exception
            throw e
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition)
        return anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
}