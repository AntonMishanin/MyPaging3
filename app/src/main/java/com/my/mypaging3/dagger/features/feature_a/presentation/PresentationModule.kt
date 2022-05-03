package com.my.mypaging3.dagger.features.feature_a.presentation


import com.my.mypaging3.dagger.core.CustomLogger
import com.my.mypaging3.dagger.features.feature_a.domain.FeatureAInteractor
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideSomeDetailPresenter(
        interactor: FeatureAInteractor,
        customLogger: CustomLogger
    ): FeatureAPresenter {
        return FeatureAPresenter.Base(interactor, customLogger)
    }
}