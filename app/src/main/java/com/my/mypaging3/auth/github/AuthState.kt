package com.my.mypaging3.auth.github

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

sealed class AuthState {

    abstract fun handle(context: Context)

    //TODO: check single event and side effect
    class ShowSiteByUri(
        private val uri: Uri,
        private val deviceCode: String
    ) : AuthState() {
        override fun handle(context: Context) {
            //TODO: use webview
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)

            Log.d("EE", deviceCode)
        }
    }

    object SuccessAuth : AuthState() {
        override fun handle(context: Context) {
            Log.d("EE", "SUCCESS AUTH")
        }
    }

    object SuccessRepoCreated : AuthState() {
        override fun handle(context: Context) {
            Log.d("EE", "SUCCESS REPO CREATED")
        }
    }
}