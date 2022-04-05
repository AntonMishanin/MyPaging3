package com.my.mypaging3.custom.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.mypaging3.R

class CustomPagingActivity : AppCompatActivity() {

    private val viewModel by viewModels<CustomPagingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_paging)

        val adapter = UserAdapter()
        val list = findViewById<RecyclerView>(R.id.list)
        list.adapter = adapter

        //TODO: create custom class OnScrollListener
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition = when (val layoutManager = list.layoutManager) {
                    is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
                    is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
                    else -> throw IllegalArgumentException("Unknown layoutManager = $layoutManager")
                }
                viewModel.fetchUsersByPage(lastVisibleItemPosition)
            }
        })

        viewModel.contentObservable.observe(this) { content ->
            adapter.submitList(content)
        }
    }
}