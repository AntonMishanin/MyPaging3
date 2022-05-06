package com.my.mypaging3.dagger.features.feature_b.feature_c

import com.my.mypaging3.dagger.core.CustomLogger
import com.my.mypaging3.dagger.features.feature_b.common.CommonInteractor
import com.my.mypaging3.dagger.features.feature_b.common.CommonModule
import dagger.Module
import dagger.Provides

@Module(includes = [CommonModule::class])
class FeatureCModule {

    @Provides
    fun provideViewModelFactory(commonInteractor: CommonInteractor, customLogger: CustomLogger): FeatureCViewModelFactory {
        return FeatureCViewModelFactory(commonInteractor, customLogger)
    }
}