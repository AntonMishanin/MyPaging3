package com.my.mypaging3.dagger.features.feature_a.domain

class FeatureAInteractor(
    private val repository: FeatureARepository
) {

    fun requestData() = repository.requestData()
}