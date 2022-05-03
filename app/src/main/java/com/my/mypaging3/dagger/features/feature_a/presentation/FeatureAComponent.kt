package com.my.mypaging3.dagger.features.feature_a.presentation

import com.my.mypaging3.dagger.core.LoggerModule
import com.my.mypaging3.dagger.features.feature_a.data.DataModule
import com.my.mypaging3.dagger.features.feature_a.domain.DomainModule
import dagger.Component

@Component(
    modules = [PresentationModule::class, DomainModule::class, DataModule::class, LoggerModule::class],
    dependencies = [FeatureADependencies::class]
)
interface FeatureAComponent {

    fun provideSomeDetailPresenter(): FeatureAPresenter

    fun inject(daggerActivity: FeatureAActivity)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: FeatureADependencies): Builder

        fun build(): FeatureAComponent
    }
}