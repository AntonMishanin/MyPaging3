package com.my.mypaging3.library.data.local

import com.my.mypaging3.library.domain.UserRepository

class LocalUserRepository(
    private val dataSource: LocalUserDataSource
) : UserRepository {
    override suspend fun fetchUsersByPage(page: Int) = dataSource.fetchUsers(page)
}