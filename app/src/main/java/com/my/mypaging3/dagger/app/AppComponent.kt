package com.my.mypaging3.dagger.app

import com.my.mypaging3.dagger.features.feature_a.presentation.FeatureAComponent
import dagger.Component

@Component(dependencies = [FeatureAComponent::class])
interface AppComponent {

    fun providePresentationComponent(): FeatureAComponent

    @Component.Builder
    interface Builder {

        fun withFeatureAComponent(featureAComponent: FeatureAComponent): Builder

        fun build(): AppComponent
    }
}