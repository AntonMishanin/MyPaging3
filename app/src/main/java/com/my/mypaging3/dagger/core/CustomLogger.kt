package com.my.mypaging3.dagger.core

import android.util.Log

interface CustomLogger {

    fun logDebug(message: String)

    class Base : CustomLogger {
        override fun logDebug(message: String) {
            Log.d("EE", message)
        }
    }
}