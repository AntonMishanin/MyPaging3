package com.my.mypaging3

import android.app.Application
import com.my.mypaging3.dagger.app.AppComponent
import com.my.mypaging3.dagger.app.DaggerAppComponent
import com.my.mypaging3.dagger.features.feature_a.presentation.DaggerFeatureAComponent

class App : Application() {

    lateinit var applicationComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent
            .builder()
            .withFeatureAComponent(DaggerFeatureAComponent.create())
            .build()
    }
}