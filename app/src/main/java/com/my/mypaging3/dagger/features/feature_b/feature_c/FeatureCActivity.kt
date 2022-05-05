package com.my.mypaging3.dagger.features.feature_b.feature_c

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.my.mypaging3.R

class FeatureCActivity : AppCompatActivity() {

    // private val componentProvider: ComponentProviderViewModel by viewModels()

    private val viewModel: FeatureCViewModel by viewModels {
        com.my.mypaging3.dagger.features.feature_b.presentation.di.ComponentProviderViewModel.getStatic()
            .provideCViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_c)

        viewModel.observe()
    }
}