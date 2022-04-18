package com.my.mypaging3.auth

import io.reactivex.Single

class GitHubDataSource(private val api: GitHubApi) {
    fun fetchUsers(): Single<List<User>> = api.fetchNowPlaying()

    fun fetchSessionCode(clientId: String) = api.fetchSessionCode(clientId)
}