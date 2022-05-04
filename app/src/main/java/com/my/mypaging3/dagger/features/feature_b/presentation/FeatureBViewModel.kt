package com.my.mypaging3.dagger.features.feature_b.presentation

import androidx.lifecycle.ViewModel
import com.my.mypaging3.dagger.core.CustomLogger

class FeatureBViewModel(
    private val customLogger: CustomLogger,
    private val id: Int
) : ViewModel() {

    init {
        customLogger.debug(id.toString())
    }

    fun fetchContent(){
        customLogger.debug("fetchContent")
    }
}