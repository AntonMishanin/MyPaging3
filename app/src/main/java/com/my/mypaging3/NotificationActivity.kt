package com.my.mypaging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.TaskStackBuilder

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        val message = intent.extras?.getString("message") ?: "EMPTY"
        findViewById<TextView>(R.id.message).text = message
    }
}