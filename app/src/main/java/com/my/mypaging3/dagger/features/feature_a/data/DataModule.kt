package com.my.mypaging3.dagger.features.feature_a.data

import dagger.Module
import dagger.Provides

@Module(includes = [DataBindModule::class])
class DataModule {

    @Provides
    fun provideDataSource() = FeatureADataSource()
}