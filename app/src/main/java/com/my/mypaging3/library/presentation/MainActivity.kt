package com.my.mypaging3.library.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.my.mypaging3.R
import com.my.mypaging3.library.di.LibraryFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: use VMFactory
        val viewModel = LibraryFactory().provideViewModel()

        val pagingAdapter = UserAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = pagingAdapter

        lifecycleScope.launch {
            viewModel.content.collect {
                Log.d("EE", "SIZE = $it")
                pagingAdapter.submitData(it)
            }
        }
    }
}