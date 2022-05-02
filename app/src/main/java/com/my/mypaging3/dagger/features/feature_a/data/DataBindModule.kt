package com.my.mypaging3.dagger.features.feature_a.data

import com.my.mypaging3.dagger.features.feature_a.domain.FeatureARepository
import dagger.Binds
import dagger.Module

/**
 * I use it only for study
 */

@Module
interface DataBindModule {

    @Binds
    fun provideSomeRepositoryRemote(someRepositoryRemote: FeatureARepositoryRemote): FeatureARepository
}