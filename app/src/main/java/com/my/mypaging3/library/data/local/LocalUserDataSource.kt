package com.my.mypaging3.library.data.local

import com.my.mypaging3.library.domain.User
import kotlinx.coroutines.delay

class LocalUserDataSource {
    suspend fun fetchUsers(page: Int): List<User> {
        delay(5000)
        val users = mutableListOf<User>()
        for (i in 0 until page) {
            users.add(allUsers[i])
        }
        return users.toList()
    }

    private val allUsers = listOf(
        User("0", "0"),
        User("1", "1"),
        User("2", "2"),
        User("3", "3"),
        User("4", "4"),
        User("5", "5"),
        User("6", "6"),
        User("7", "7"),
        User("8", "8"),
        User("9", "9"),
        User("10", "10"),
        User("11", "11"),
        User("12", "12"),
        User("13", "13"),
        User("14", "14"),
        User("15", "15"),
        User("16", "16"),
        User("17", "17"),
        User("18", "18"),
        User("19", "19"),
    )
}