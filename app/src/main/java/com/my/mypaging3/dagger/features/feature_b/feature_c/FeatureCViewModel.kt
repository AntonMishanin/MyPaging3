package com.my.mypaging3.dagger.features.feature_b.feature_c

import android.util.Log
import androidx.lifecycle.ViewModel
import com.my.mypaging3.dagger.features.feature_b.common.CommonInteractor

class FeatureCViewModel(private val commonInteractor: CommonInteractor) : ViewModel() {

    init {
        Log.d("EE", "FeatureCViewModel")
    }

    fun observe() {

    }
}