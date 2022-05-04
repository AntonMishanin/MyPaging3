package com.my.mypaging3.dagger.features.feature_b.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.mypaging3.dagger.core.CustomLogger

class FeatureBViewModelFactory(private val customLogger: CustomLogger) : ViewModelProvider.Factory {

    private var id: Int = ID_DEFAULT_VALUE

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeatureBViewModel(customLogger, id) as T
    }

    fun setParam(id: Int) {
        this.id = id
    }

    private companion object {
        private const val ID_DEFAULT_VALUE = 0
    }
}