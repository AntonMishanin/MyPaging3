package com.my.mypaging3.library.domain

interface UserRepository {
    fun fetchUsersByPage(page: Int): List<User>
}