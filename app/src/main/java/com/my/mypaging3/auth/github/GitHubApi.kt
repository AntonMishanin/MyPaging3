package com.my.mypaging3.auth.github

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

//TODO: split auth api and resource api
interface GitHubApi {
    @GET("/users")
    fun fetchNowPlaying(): Single<List<User>>

    @GET("/users")
    fun fetchNowPlaying2(): Call<List<Response>>

    @Headers("Accept: application/json")
    @POST("/login/device/code")
    fun fetchSessionCode(
        @Query("client_id") client_id: String,
        @Query("scope") scope: String = "repo"// TODO: move out scope
    ): Single<Response>

    @Headers("Accept: application/json")
    @POST("/login/oauth/access_token")
    fun checkToken(
        @Query("client_id") clientId: String,
        @Query("device_code") deviceCode: String,
        @Query("grant_type") grantType: String
    ): Single<TokenResponse.Full>

    @Headers("Accept: application/vnd.github.v3+json")
    @POST("/user/repos")
    fun createRepository(
        @Header("Authorization") token: String,
        @Body name: RepositoryRequest
    ): Single<RepositoryResponse>
}

//TODO: rename
data class Response(
    val device_code: String,
    val user_code: String
)

interface TokenResponse{

    data class Full(
        val access_token: String?,
        val error: String?
    ): TokenResponse

    //TODO: move as domain model
    data class Success(
        val access_token: String
    ): TokenResponse

    data class Error(
        val error: String
    ): TokenResponse
}

data class RepositoryResponse(
    val id: Long
)

data class RepositoryRequest(
    val name: String
)