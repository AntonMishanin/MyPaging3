package com.my.mypaging3.dagger.core

import android.content.Context
import android.util.Log

interface CustomLogger {

    fun debug(message: String)

    class Base(private val context: Context) : CustomLogger {

        init {
            Log.d("EE", "LOGGER INIT")
        }

        override fun debug(message: String) {
            Log.d("EE", message)
        }
    }
}