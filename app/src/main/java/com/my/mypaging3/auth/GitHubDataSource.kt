package com.my.mypaging3.auth

import io.reactivex.Single

class GitHubDataSource(private val api: GitHubApi) {
    fun fetchUsers(): Single<List<User>> = api.fetchNowPlaying()

    fun fetchSessionCode(clientId: String) = api.fetchSessionCode(clientId)

    fun checkToken(clientId: String, deviceCode: String, grantType: String) =
        api.checkToken(clientId, deviceCode, grantType)

    fun createRepository(name: String) = api.createRepository(
        RepositoryRequest(name)
    )
}