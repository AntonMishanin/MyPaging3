package com.my.mypaging3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.my.mypaging3.custom.presentation.CustomPagingActivity
import com.my.mypaging3.library.presentation.MainActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        findViewById<View>(R.id.custom).setOnClickListener {
            startActivity(Intent(this, CustomPagingActivity::class.java))
        }

        findViewById<View>(R.id.library).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}