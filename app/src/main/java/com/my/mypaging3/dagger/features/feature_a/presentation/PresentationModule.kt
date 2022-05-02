package com.my.mypaging3.dagger.features.feature_a.presentation

import com.my.mypaging3.dagger.features.feature_a.data.DataModule
import com.my.mypaging3.dagger.features.feature_a.domain.DomainModule
import com.my.mypaging3.dagger.features.feature_a.domain.FeatureAInteractor
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideSomeDetailPresenter(interactor: FeatureAInteractor): FeatureAPresenter {
        return FeatureAPresenter.Base(interactor)
    }
}