package com.my.mypaging3.dagger.features.feature_b.presentation.di

import androidx.lifecycle.ViewModel
import com.my.mypaging3.dagger.core.DefaultFeatureDependencies
import java.lang.NullPointerException

class ComponentProviderViewModel(
    defaultFeatureDependencies: DefaultFeatureDependencies
) : ViewModel() {

    init {
        component = DaggerFeatureBComponent
            .builder()
            .dependencies(defaultFeatureDependencies)
            .build()
    }

    //private val component: FeatureBComponent = DaggerFeatureBComponent
    //    .builder()
    //    .dependencies(defaultFeatureDependencies)
    //    .build()

    fun get() = component!!

    companion object {
        private var component: FeatureBComponent? = null

        fun getStatic() = component
            ?: throw NullPointerException("You must trigger ComponentProviderViewModel - invoke get()")
    }

    override fun onCleared() {
        super.onCleared()
        component = null
    }
}