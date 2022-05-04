package com.my.mypaging3.dagger.features.feature_b.presentation.di

import com.my.mypaging3.dagger.core.CustomLogger
import com.my.mypaging3.dagger.features.feature_b.presentation.FeatureBViewModelFactory
import com.my.mypaging3.test.domain.EmailValidator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentationModule {

    @Singleton
    @Provides
    fun provideFeatureBViewModelFactory(customLogger: CustomLogger): FeatureBViewModelFactory {
        return FeatureBViewModelFactory(customLogger)
    }

    @Provides
    fun provideEmailValidator(): EmailValidator {
        return EmailValidator()
    }
}