package com.my.mypaging3.dagger.app

import android.content.Context
import com.my.mypaging3.BuildConfig
import com.my.mypaging3.dagger.features.feature_a.presentation.FeatureAComponent
import com.my.mypaging3.dagger.features.feature_b.presentation.FeatureBComponent
import com.my.mypaging3.dagger.features.feature_b.presentation.FeatureBDependencies
import dagger.BindsInstance
import dagger.Component
import dagger.Provides

@Component(dependencies = [FeatureAComponent::class, FeatureBComponent::class])
interface AppComponent {

    fun provideFeatureAComponent(): FeatureAComponent

    fun provideFeatureBComponent(): FeatureBComponent

    @Component.Builder
    interface Builder {

        fun withFeatureAComponent(featureAComponent: FeatureAComponent): Builder
        fun withFeatureBComponent(featureBComponent: FeatureBComponent): Builder

        fun build(): AppComponent
    }
}