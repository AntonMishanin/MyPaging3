package com.my.mypaging3.dagger.features.feature_a.presentation

import com.my.mypaging3.dagger.core.CustomLogger
import com.my.mypaging3.dagger.features.feature_a.domain.FeatureAInteractor

interface FeatureAPresenter {

    fun onViewAttached(view: FeatureAView)

    class Base(
        private val interactor: FeatureAInteractor,
        private val logger: CustomLogger
    ) : FeatureAPresenter {

        override fun onViewAttached(view: FeatureAView) {
            logger.debug("onViewAttached")
        }
    }

    class NullObject() : FeatureAPresenter {
        override fun onViewAttached(view: FeatureAView) = Unit
    }
}