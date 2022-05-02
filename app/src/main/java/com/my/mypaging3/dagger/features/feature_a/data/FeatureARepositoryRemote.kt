package com.my.mypaging3.dagger.features.feature_a.data

import com.my.mypaging3.dagger.features.feature_a.domain.FeatureARepository
import javax.inject.Inject

class FeatureARepositoryRemote @Inject constructor(
    private val dataSource: FeatureADataSource
) : FeatureARepository {

    override fun requestData() = dataSource.requestData()
}