package com.my.mypaging3.library.domain

import java.util.*

data class User(
    val id: String,
    val name: String
) {
    companion object {
        fun random() = User(
            id = UUID.randomUUID().toString(),
            name = UUID.randomUUID().toString()
        )
    }
}