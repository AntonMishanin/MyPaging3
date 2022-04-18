package com.my.mypaging3.auth

import io.reactivex.Single
import retrofit2.http.*

interface GitHubApi {
    @GET("/users")
    fun fetchNowPlaying(): Single<List<User>>
//"Content-Type:";"application/json"

    @Headers("Accept: application/json")
    @POST("/login/device/code")
    fun fetchSessionCode(
        @Query("client_id") client_id: String,
        //  @Query("scope") scope: String = "scope"
    ): Single<Response>

    @POST("/login/oauth/access_token")
    fun checkToken(
    //    @Query("/login/oauth/access_token")
    )
}

data class Response(
    val device_code: String,
    val user_code: String
)