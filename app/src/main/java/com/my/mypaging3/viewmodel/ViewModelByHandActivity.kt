package com.my.mypaging3.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.my.mypaging3.R

open class ViewModelByHandActivity : AppCompatActivity() {

    companion object {
        private var viewModel: ViewModelByHand? = null
    }

    class SomeClass {
        companion object {
            internal var viewModel: ViewModelByHand? = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_by_hand)
        applicationContext
        // if (savedInstanceState == null) {
        //     viewModel = ViewModelByHand()
        //     val s = SomeClass()
        //     s.viewModel = viewModel ?: ViewModelByHand()

        //     Log.d("EE", "FIRST TIME")
        // } else {
        //     viewModel = (lastNonConfigurationInstance as? SomeClass)?.viewModel

        //     Log.d("EE", "ROTATED")
        // }
        /// viewModel = (lastNonConfigurationInstance as? SomeClass)?.viewModel

        //if (savedInstanceState == null) {
        //    SomeClass.viewModel = ViewModelByHand()
        //}

        //if (SomeClass.viewModel == null) {
        //    SomeClass.viewModel = ViewModelByHand()
        //}
        //viewModel = SomeClass.viewModel!!

        if (viewModel == null) {
            viewModel = ViewModelByHand()
        }

        Log.d("EE", "onCreate VM ${viewModel?.value}")

        Log.d("EE", "onCreate $lastNonConfigurationInstance")
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        Log.d("EE", "RETAIN")
        val s = ViewModelHolder()
        // s.viewModel = ViewModelByHand()
        return s
    }

    //override fun onRetainNonConfigurationInstance(): Any?{
    //    return "1111111111"
    //}

    override fun getLastCustomNonConfigurationInstance(): Any? {
        return super.getLastCustomNonConfigurationInstance()
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
        Log.d("EE", "onResume $lastNonConfigurationInstance")
    }

    override fun onPause() {
        super.onPause()
        Log.d("EE", "onPause")
        viewModel?.value = (1..5000000).random()
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

class ViewModelHolder {
    companion object {
        lateinit var viewModel: ViewModelByHand
    }

}