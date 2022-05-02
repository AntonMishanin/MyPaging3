package com.my.mypaging3.dagger.features.feature_a.domain

import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideFeatureAInteractor(featureARepository: FeatureARepository): FeatureAInteractor {
        return FeatureAInteractor(featureARepository)
    }
}