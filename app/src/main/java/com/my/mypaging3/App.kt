package com.my.mypaging3

import android.app.Application
import android.content.Context
import com.my.mypaging3.dagger.app.AppComponent
import com.my.mypaging3.dagger.app.DaggerAppComponent
import com.my.mypaging3.dagger.features.feature_a.presentation.DaggerFeatureAComponent
import com.my.mypaging3.dagger.features.feature_a.presentation.FeatureADependencies
import com.my.mypaging3.dagger.features.feature_b.presentation.DaggerFeatureBComponent
import com.my.mypaging3.dagger.features.feature_b.presentation.FeatureBDependencies

class App : Application() {

    lateinit var applicationComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent
            .builder()
            .withFeatureAComponent(DaggerFeatureAComponent.builder().dependencies(FeatureADependenciesImpl()).build())
            .withFeatureBComponent(DaggerFeatureBComponent.builder().dependencies(FeatureBDependenciesImpl()).build())
            .build()
    }

    inner class FeatureADependenciesImpl : FeatureADependencies {
        override fun context(): Context = this@App
    }

    inner class FeatureBDependenciesImpl : FeatureBDependencies {
        override fun context(): Context = this@App
    }
}