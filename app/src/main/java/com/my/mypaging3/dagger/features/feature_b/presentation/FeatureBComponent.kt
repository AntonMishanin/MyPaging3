package com.my.mypaging3.dagger.features.feature_b.presentation

import com.my.mypaging3.dagger.core.LoggerModule
import dagger.Component

@Component(
    modules = [PresentationModule::class, LoggerModule::class],
    dependencies = [FeatureBDependencies::class]
)
interface FeatureBComponent {

    fun provideViewModelFactory(): FeatureBViewModelFactory

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: FeatureBDependencies): Builder

        fun build(): FeatureBComponent
    }
}