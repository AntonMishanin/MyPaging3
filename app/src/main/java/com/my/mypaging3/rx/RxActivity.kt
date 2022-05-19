package com.my.mypaging3.rx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.my.mypaging3.R

class RxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx)

        val observableHelper = ObservableHelper()
        //observableHelper.checkObservableJust()
        //observableHelper.checkObservableFrom()
        //observableHelper.checkObservableCreate()
        //observableHelper.checkObservableDistinct()
        observableHelper.checkConcatWith()
    }
}