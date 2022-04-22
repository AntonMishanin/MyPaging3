package com.my.mypaging3.okhttp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.my.mypaging3.R
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import java.io.IOException


class OkHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)

        val client: OkHttpClient = OkHttpClient.Builder()
            //.authenticator()
            .authenticator(object : Authenticator{
                override fun authenticate(route: Route?, response: Response): Request? {
                   Log.d("EE", "auth response = $response")

                    val url = ("https://api.github.com" + "/users").toHttpUrlOrNull()
                        ?.newBuilder()
                        ?.addQueryParameter("", "")
                        ?.build()!!

                    return Request.Builder()
                        .url(url)
                        .get()
                        .build()
                }
            })
            .build()

        val url = ("https://api.github.com" + "/users").toHttpUrlOrNull()
            ?.newBuilder()
            ?.addQueryParameter("", "")
            ?.build()
            ?: return

        val request: Request = Request.Builder()
            .url(url)
            .get()
            .build()

        val call: Call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("EE", "e $e")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("EE", "response $response")
                Log.d("EE", "responseBody ${response.body}")
                Log.d("EE", "responseBodySource ${response.body?.source()}")
            }
        })
    }
}