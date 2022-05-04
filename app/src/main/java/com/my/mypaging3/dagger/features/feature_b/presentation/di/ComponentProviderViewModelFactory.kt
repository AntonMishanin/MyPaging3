package com.my.mypaging3.dagger.features.feature_b.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.mypaging3.dagger.core.DefaultFeatureDependencies

class ComponentProviderViewModelFactory(
    private val defaultFeatureDependencies: DefaultFeatureDependencies
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        ComponentProviderViewModel(defaultFeatureDependencies) as T
}