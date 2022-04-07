package com.my.mypaging3.work

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class NotificationActions : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("EE", "intent?.action = ${intent?.getStringExtra("action")}")
        when (intent?.action) {
            "YES_ACTION" -> Toast.makeText(context, "YES CALLED", Toast.LENGTH_SHORT).show()
            "STOP_ACTION" -> Toast.makeText(context, "STOP CALLED", Toast.LENGTH_SHORT).show()
        }

        //This is used to close the notification tray
        val it = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
        context?.sendBroadcast(it)
    }
}