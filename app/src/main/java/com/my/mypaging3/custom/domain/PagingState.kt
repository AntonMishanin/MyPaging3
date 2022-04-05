package com.my.mypaging3.custom.domain

import com.my.mypaging3.library.domain.User

sealed class PagingState {
    data class Content(val users: List<User>) : PagingState()
    object SkipRequest : PagingState()
    object Loading : PagingState()
    object Error : PagingState()
}