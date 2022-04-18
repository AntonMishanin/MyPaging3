package com.my.mypaging3.auth

data class UsersResponse (
    val users: User
        )

data class User(
    val login: String
)