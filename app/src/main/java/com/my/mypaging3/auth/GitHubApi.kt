package com.my.mypaging3.auth

import io.reactivex.Single
import retrofit2.http.*

interface GitHubApi {
    @GET("/users")
    fun fetchNowPlaying(): Single<List<User>>

    @Headers("Accept: application/json")
    @POST("/login/device/code")
    fun fetchSessionCode(
        @Query("client_id") client_id: String,
        @Query("scope") scope: String = "repo"
    ): Single<Response>

    @Headers("Accept: application/json")
    @POST("/login/oauth/access_token")
    fun checkToken(
        @Query("client_id") clientId: String,
        @Query("device_code") deviceCode: String,
        @Query("grant_type") grantType: String
    ): Single<TokenResponse>

    @Headers("Accept: application/vnd.github.v3+json")
    @POST("/user/repos")
    fun createRepository(@Body name: RepositoryRequest): Single<RepositoryResponse>
}

data class Response(
    val device_code: String,
    val user_code: String
)

data class TokenResponse(
    val access_token: String
)

data class RepositoryResponse(
    val id: Long
)

data class RepositoryRequest(
    val name: String
)