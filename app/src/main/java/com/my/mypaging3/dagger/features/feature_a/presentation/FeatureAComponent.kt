package com.my.mypaging3.dagger.features.feature_a.presentation

import com.my.mypaging3.dagger.core.DefaultFeatureDependencies
import com.my.mypaging3.dagger.core.LoggerModule
import com.my.mypaging3.dagger.core.PerFeature
import com.my.mypaging3.dagger.features.feature_a.data.DataModule
import com.my.mypaging3.dagger.features.feature_a.domain.DomainModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@PerFeature
@Component(
    modules = [PresentationModule::class, DomainModule::class, DataModule::class, LoggerModule::class],
    dependencies = [DefaultFeatureDependencies::class]
)
interface FeatureAComponent {

    fun provideSomeDetailPresenter(): FeatureAPresenter

    fun inject(daggerActivity: FeatureAActivity)

    @Component.Builder
    interface Builder {

        fun dependencies(defaultFeatureDependencies: DefaultFeatureDependencies): Builder

        fun build(): FeatureAComponent
    }
}