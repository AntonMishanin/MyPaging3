package com.my.mypaging3.dagger.features.feature_b.presentation.di

import com.my.mypaging3.dagger.core.CustomLogger
import com.my.mypaging3.dagger.core.PerFeature
import com.my.mypaging3.dagger.features.feature_b.presentation.FeatureBViewModelFactory
import com.my.mypaging3.test.domain.EmailValidator
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @PerFeature
    @Provides
    fun provideFeatureBViewModelFactory(customLogger: CustomLogger): FeatureBViewModelFactory {
        return FeatureBViewModelFactory(customLogger)
    }

    @Provides
    fun provideEmailValidator(): EmailValidator {
        return EmailValidator()
    }
}