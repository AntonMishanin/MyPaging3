package com.my.mypaging3.dagger.features.feature_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.my.mypaging3.App
import com.my.mypaging3.R

class FeatureBActivity : AppCompatActivity() {

    private val viewModel: FeatureBViewModel by viewModels {
        (application as App).applicationComponent.provideFeatureBComponent().provideViewModelFactory().apply {
            setParam(3)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_bactivity)

        viewModel.fetchContent()
    }
}