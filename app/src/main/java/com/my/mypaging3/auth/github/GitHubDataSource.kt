package com.my.mypaging3.auth.github

import io.reactivex.Single

class GitHubDataSource(private val api: GitHubApi) {
    fun fetchUsers(): Single<List<User>> = api.fetchNowPlaying()

    fun fetchSessionCode(clientId: String) = api.fetchSessionCode(clientId)

    //TODO: move mapping logic out
    fun checkToken(
        clientId: String,
        deviceCode: String,
        grantType: String
    ): Single<TokenResponse> =
        api.checkToken(clientId, deviceCode, grantType)
            .map { response ->
                if (response.access_token.isNullOrEmpty()) {
                    TokenResponse.Error(response.error ?: "")
                } else {
                    TokenResponse.Success(response.access_token)
                }
            }

    fun createRepository(token: String, name: String) = api.createRepository(
        token,
        RepositoryRequest(name)//TODO: move out this logic
    )
}