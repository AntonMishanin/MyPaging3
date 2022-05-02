package com.my.mypaging3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.my.mypaging3.auth.AuthActivity
import com.my.mypaging3.custom.presentation.CustomPagingActivity
import com.my.mypaging3.custom_view.LearnViewActivity
import com.my.mypaging3.dagger.features.feature_a.presentation.FeatureAActivity
import com.my.mypaging3.dynamic_proxy.DynamicProxyActivity
import com.my.mypaging3.library.presentation.MainActivity
import com.my.mypaging3.okhttp.OkHttpActivity
import com.my.mypaging3.room.RoomActivity
import com.my.mypaging3.test.presentation.LoginActivity
import com.my.mypaging3.viewmodel.ViewModelByHandActivity
import com.my.mypaging3.websocket.WebSocketActivity
import com.my.mypaging3.work.WorkActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        findViewById<View>(R.id.dagger).setOnClickListener {
            startActivity(Intent(this, FeatureAActivity::class.java))
        }

        findViewById<View>(R.id.lesson_tests).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        findViewById<View>(R.id.websocket).setOnClickListener {
            startActivity(Intent(this, WebSocketActivity::class.java))
        }

        findViewById<View>(R.id.okhttp).setOnClickListener {
            startActivity(Intent(this, OkHttpActivity::class.java))
        }

        findViewById<View>(R.id.dynamic_proxy).setOnClickListener {
            startActivity(Intent(this, DynamicProxyActivity::class.java))
        }

        findViewById<View>(R.id.remote_auth).setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
        }

        findViewById<View>(R.id.room).setOnClickListener {
            startActivity(Intent(this, RoomActivity::class.java))
        }

        findViewById<View>(R.id.custom_view).setOnClickListener {
            startActivity(Intent(this, LearnViewActivity::class.java))
        }

        findViewById<View>(R.id.viewModel).setOnClickListener {
            startActivity(Intent(this, ViewModelByHandActivity::class.java))
        }

        findViewById<View>(R.id.work_manager).setOnClickListener {
            startActivity(Intent(this, WorkActivity::class.java))
        }

        findViewById<View>(R.id.custom).setOnClickListener {
            startActivity(Intent(this, CustomPagingActivity::class.java))
        }

        findViewById<View>(R.id.library).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}