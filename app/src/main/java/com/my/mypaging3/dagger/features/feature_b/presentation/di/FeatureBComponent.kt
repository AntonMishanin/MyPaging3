package com.my.mypaging3.dagger.features.feature_b.presentation.di

import com.my.mypaging3.dagger.core.DefaultFeatureDependencies
import com.my.mypaging3.dagger.core.LoggerModule
import com.my.mypaging3.dagger.features.feature_b.presentation.FeatureBViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [PresentationModule::class, LoggerModule::class],
    dependencies = [DefaultFeatureDependencies::class]
)
interface FeatureBComponent {

    fun provideViewModelFactory(): FeatureBViewModelFactory

    @Component.Builder
    interface Builder {

        fun dependencies(dependenciesDefault: DefaultFeatureDependencies): Builder

        fun build(): FeatureBComponent
    }
}