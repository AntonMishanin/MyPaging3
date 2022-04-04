package com.my.mypaging3.library.data.local

import com.my.mypaging3.library.domain.User

class LocalUserDataSource {
    fun fetchUsers(page: Int): List<User>{
        val users = mutableListOf<User>()
        for(i in 0 until page){
            users.add(User.random())
        }
        return users
    }
}