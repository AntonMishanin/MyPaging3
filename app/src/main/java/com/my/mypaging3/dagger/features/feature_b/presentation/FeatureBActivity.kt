package com.my.mypaging3.dagger.features.feature_b.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.my.mypaging3.R
import com.my.mypaging3.dagger.core.DefaultFeatureDependencies
import com.my.mypaging3.dagger.features.feature_b.feature_c.FeatureCActivity
import com.my.mypaging3.dagger.features.feature_b.presentation.di.ComponentProviderViewModel
import com.my.mypaging3.dagger.features.feature_b.presentation.di.ComponentProviderViewModelFactory

class FeatureBActivity : AppCompatActivity() {

    private val componentProvider: ComponentProviderViewModel by viewModels {
        val dependencies = this.application as DefaultFeatureDependencies
        ComponentProviderViewModelFactory(dependencies)
    }

    private val viewModel: FeatureBViewModel by viewModels {
        componentProvider.get().provideViewModelFactory().apply {
            setParam(3)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.fetchContent()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_bactivity)

        findViewById<View>(R.id.root_view).setOnClickListener {
            startActivity(Intent(this, FeatureCActivity::class.java))
        }
    }
}