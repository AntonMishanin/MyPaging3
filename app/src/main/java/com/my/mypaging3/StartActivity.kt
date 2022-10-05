package com.my.mypaging3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.my.mypaging3.auth.AuthActivity
import com.my.mypaging3.coroutines.CoroutinesActivity
import com.my.mypaging3.custom.presentation.CustomPagingActivity
import com.my.mypaging3.custom_view.LearnViewActivity
import com.my.mypaging3.dagger.features.feature_a.presentation.FeatureAActivity
import com.my.mypaging3.dynamic_proxy.DynamicProxyActivity
import com.my.mypaging3.library.presentation.MainActivity
import com.my.mypaging3.location.LocationActivity
import com.my.mypaging3.okhttp.OkHttpActivity
import com.my.mypaging3.room.RoomActivity
import com.my.mypaging3.rx.RxActivity
import com.my.mypaging3.test.presentation.LoginActivity
import com.my.mypaging3.thread.ThreadActivity
import com.my.mypaging3.viewmodel.ViewModelByHandActivity
import com.my.mypaging3.websocket.WebSocketActivity
import com.my.mypaging3.work.WorkActivity

class StartActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        firebaseAnalytics.logEvent(
            "CREATE",
            bundleOf(
                Pair("KEY1", "VALUE1"),
                Pair("KEY2", "VALUE2")
            )
        )

        intent.extras?.let {
            for (key in it.keySet()) {
                val value = intent.extras?.get(key)
                Log.d("MAIN AC", "Key: $key Value: $value")
                if (value != null) {
                    val intent = Intent(this, NotificationActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.putExtra("message", value.toString()+"FROM ACTIIVTY")
                    startActivity(intent)
                }
            }
        }

        findViewById<View>(R.id.location).setOnClickListener {
            logEvent()
            startActivity(Intent(this, LocationActivity::class.java))
        }

        findViewById<View>(R.id.thread).setOnClickListener {
            logEvent()
            startActivity(Intent(this, ThreadActivity::class.java))
        }

        findViewById<View>(R.id.coroutines).setOnClickListener {
            logEvent()
            startActivity(Intent(this, CoroutinesActivity::class.java))
        }

        findViewById<View>(R.id.rx).setOnClickListener {
            startActivity(Intent(this, RxActivity::class.java))
        }

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

    override fun onStart() {
        super.onStart()
        firebaseAnalytics.logEvent(
            "onStart",
            bundleOf(
                Pair("KEY1", "VALUE1"),
                Pair("KEY2", "VALUE2")
            )
        )
    }

    override fun onResume() {
        super.onResume()
        firebaseAnalytics.logEvent(
            "onResume",
            bundleOf(
                Pair("KEY1", "VALUE1"),
                Pair("KEY2", "VALUE2")
            )
        )
    }

    override fun onPause() {
        super.onPause()
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.SELECT_ITEM,
            bundleOf(
                Pair("KEY1", "VALUE1"),
                Pair("KEY2", "VALUE2")
            )
        )
    }

    private fun logEvent(){
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.SELECT_ITEM,
            bundleOf(
                Pair("KEY1", "VALUE1"),
                Pair("KEY2", "VALUE2")
            )
        )
    }
}