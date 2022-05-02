package com.my.mypaging3.dagger.features.feature_a.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.my.mypaging3.App
import com.my.mypaging3.R
import javax.inject.Inject

class FeatureAActivity : AppCompatActivity(), FeatureAView {

    @Inject
    lateinit var presenter: FeatureAPresenter// = SomeDetailPresenter.NullObject()

    //private val presenter: SomeDetailPresenter by lazy {
    //    (application as App).applicationComponent.provideSomeDetailPresenter()
    //}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)
        (application as App).applicationComponent.providePresentationComponent().inject(this)
        presenter.onViewAttached(this)
    }
}

