package com.my.mypaging3.library.domain

interface UserRepository {
    suspend fun fetchUsersByPage(page: Int): List<User>
}