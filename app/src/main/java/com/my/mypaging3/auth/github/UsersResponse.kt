package com.my.mypaging3.auth.github

data class UsersResponse (
    val users: User
        )

data class User(
    val login: String
)