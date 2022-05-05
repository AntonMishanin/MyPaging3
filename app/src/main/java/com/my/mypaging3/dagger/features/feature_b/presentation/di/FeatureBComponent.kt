package com.my.mypaging3.dagger.features.feature_b.presentation.di

import com.my.mypaging3.dagger.core.DefaultFeatureDependencies
import com.my.mypaging3.dagger.core.LoggerModule
import com.my.mypaging3.dagger.core.PerFeature
import com.my.mypaging3.dagger.features.feature_b.feature_c.FeatureCModule
import com.my.mypaging3.dagger.features.feature_b.feature_c.FeatureCViewModelFactory
import com.my.mypaging3.dagger.features.feature_b.presentation.FeatureBViewModelFactory
import dagger.Component
import javax.inject.Singleton

@PerFeature
@Component(
    modules = [PresentationModule::class, LoggerModule::class, FeatureCModule::class],
    dependencies = [DefaultFeatureDependencies::class]
)
interface FeatureBComponent {

    fun provideViewModelFactory(): FeatureBViewModelFactory

    fun provideCViewModelFactory(): FeatureCViewModelFactory

    @Component.Builder
    interface Builder {

        fun dependencies(dependenciesDefault: DefaultFeatureDependencies): Builder

        fun build(): FeatureBComponent
    }
}