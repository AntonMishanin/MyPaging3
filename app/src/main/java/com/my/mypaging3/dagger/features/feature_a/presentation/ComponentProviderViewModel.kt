package com.my.mypaging3.dagger.features.feature_a.presentation

import androidx.lifecycle.ViewModel
import com.my.mypaging3.dagger.core.DefaultFeatureDependencies

class ComponentProviderViewModel(
    defaultFeatureDependencies: DefaultFeatureDependencies
) : ViewModel() {

    private val component = DaggerFeatureAComponent
        .builder()
        .dependencies(defaultFeatureDependencies)
        .build()

    fun get() = component
}