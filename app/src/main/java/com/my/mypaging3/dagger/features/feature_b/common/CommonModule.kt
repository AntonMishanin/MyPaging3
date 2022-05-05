package com.my.mypaging3.dagger.features.feature_b.common

import com.my.mypaging3.dagger.core.PerFeature
import dagger.Module
import dagger.Provides

@Module
class CommonModule {

    @Provides
    @PerFeature
    fun provideCommonInteractor(): CommonInteractor{
        return CommonInteractor()
    }
}