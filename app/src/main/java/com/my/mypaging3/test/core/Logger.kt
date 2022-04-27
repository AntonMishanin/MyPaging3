package com.my.mypaging3.test.core

import android.util.Log

interface Logger {

    fun log(message: String)

    class Debug : Logger {
        override fun log(message: String) {
            Log.d("EE", message)
        }
    }

    class Fake : Logger {
        override fun log(message: String) = Unit
    }
}