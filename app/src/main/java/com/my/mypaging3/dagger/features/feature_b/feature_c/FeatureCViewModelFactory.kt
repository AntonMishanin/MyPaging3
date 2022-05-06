package com.my.mypaging3.dagger.features.feature_b.feature_c

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.mypaging3.dagger.core.CustomLogger
import com.my.mypaging3.dagger.features.feature_b.common.CommonInteractor

class FeatureCViewModelFactory(private val commonInteractor: CommonInteractor, private val customLogger: CustomLogger) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeatureCViewModel(commonInteractor, customLogger) as T
    }
}