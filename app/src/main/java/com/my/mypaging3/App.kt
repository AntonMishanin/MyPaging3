package com.my.mypaging3

import android.app.Application
import android.content.Context
import com.my.mypaging3.dagger.core.DefaultFeatureDependencies

class App : Application(), DefaultFeatureDependencies {

    override fun context(): Context = this@App
}