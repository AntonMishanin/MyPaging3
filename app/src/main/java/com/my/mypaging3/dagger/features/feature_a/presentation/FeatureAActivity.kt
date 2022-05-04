package com.my.mypaging3.dagger.features.feature_a.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.my.mypaging3.R
import com.my.mypaging3.dagger.core.DefaultFeatureDependencies
import com.my.mypaging3.dagger.features.feature_b.presentation.FeatureBActivity
import javax.inject.Inject

class FeatureAActivity : AppCompatActivity(), FeatureAView {

    @Inject
    lateinit var presenter: FeatureAPresenter// = SomeDetailPresenter.NullObject()

    //private val presenter: SomeDetailPresenter by lazy {
    //    (application as App).applicationComponent.provideSomeDetailPresenter()
    //}

    private val componentProvider: ComponentProviderViewModel by viewModels {
        val dependencies = this.application as DefaultFeatureDependencies
        ComponentProviderViewModelFactory(dependencies)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)

        componentProvider.get().inject(this)
        presenter.onViewAttached(this)

        findViewById<View>(R.id.root_view).setOnClickListener {
            startActivity(Intent(this, FeatureBActivity::class.java))
        }
    }
}

