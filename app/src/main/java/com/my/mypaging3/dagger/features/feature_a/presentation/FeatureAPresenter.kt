package com.my.mypaging3.dagger.features.feature_a.presentation

import android.util.Log
import com.my.mypaging3.dagger.features.feature_a.domain.FeatureAInteractor

interface FeatureAPresenter {

    fun onViewAttached(view: FeatureAView)

    class Base(
        private val interactor: FeatureAInteractor
    ) : FeatureAPresenter {

        override fun onViewAttached(view: FeatureAView) {
            Log.d("EE", "onViewAttached")
        }
    }

    class NullObject() : FeatureAPresenter {
        override fun onViewAttached(view: FeatureAView) = Unit
    }
}