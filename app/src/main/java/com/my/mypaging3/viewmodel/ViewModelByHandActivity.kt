package com.my.mypaging3.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.my.mypaging3.R

class ViewModelByHandActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModelByHand

    private companion object {
        class SomeClass {
            lateinit var viewModel: ViewModelByHand
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_by_hand)

        val lastNonConfigurationInstance = lastNonConfigurationInstance as SomeClass

        if (lastNonConfigurationInstance == null) {
            val someClass = SomeClass()
            someClass.viewModel = ViewModelByHand()
            viewModel = someClass.viewModel
        }

        //
        //     val reference = savedInstanceState?.getString("KEY")
        //     if (reference == null) {
        //         Holder().viewModel = ViewModelByHand()
        //     } else {
        //         //(lastNonConfigurationInstance as Holder).viewModel

        //         //    viewModel = c.newInstance() as ViewModelByHand
        //     }

        Log.d("EE", "onCreate ${viewModel.value}")
    }

    override fun onStart() {
        super.onStart()
        Log.d("EE", "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("EE", "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("EE", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("EE", "onPause")
        viewModel.value = (1..5000000).random()
    }

    override fun onStop() {
        super.onStop()
        Log.d("EE", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("EE", "onDestroy")
    }
}

class ViewModelByHand() {
    init {
        Log.d("EE", "init ViewModelByHand")
    }

    var value = 1
}