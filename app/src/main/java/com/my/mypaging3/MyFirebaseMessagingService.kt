package com.my.mypaging3

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.lang.Exception
import java.util.logging.Handler

internal class MyFirebaseMessagingService : FirebaseMessagingService()
/*
internal class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageSent(msgId: String) {
        super.onMessageSent(msgId)
        // Toast.makeText(applicationContext, "SENT", Toast.LENGTH_LONG).show()
        Log.d("MyFirebaseMessaging", "onMessageSent")
    }

    override fun onSendError(msgId: String, exception: Exception) {
        super.onSendError(msgId, exception)
        // Toast.makeText(applicationContext, "ERROR $exception", Toast.LENGTH_LONG).show()
        Log.d("MyFirebaseMessaging", "onSendError")
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
        // Toast.makeText(applicationContext, "DELETE", Toast.LENGTH_LONG).show()
        Log.d("MyFirebaseMessaging", "onDeletedMessages")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //Toast.makeText(applicationContext, "NEW TOKEN", Toast.LENGTH_LONG).show()
        Log.d("MyFirebaseMessaging", "onNewToken")
    }

    override fun handleIntent(intent1: Intent?) {
        //intent1?.putExtra("Key", "value")
        super.handleIntent(intent1)

      // val intent = Intent(this, NotificationActivity::class.java)
      // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
      //  intent.putExtra("message", intent1?.data?.toString()?:"INTENT")
      // startActivity(intent)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        onMessageReceived(message)
        Log.d("MyFirebaseMessaging", message.toString())
        //throw Exception("onMessageReceived")
        // Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_LONG).show()
        message.notification?.let {
           // Log.d(TAG, "Message Notification Body: ${it.body}")
            it.body?.let { body -> sendNotification(body) }
        }
    }

    private fun sendNotification(messageBody: String) {
        val intent = Intent(this, NotificationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 */
/* Request code *//*
, intent,
            PendingIntent.FLAG_IMMUTABLE)

       // val channelId = getString(R.string.default_notification_channel_id)
       // val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, "channelId")
            .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
            .setContentTitle("dsd")
            .setContentText(messageBody)
            .setAutoCancel(true)
            //.setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("channelId",
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 */
/* ID of notification *//*
, notificationBuilder.build())
    }
}*/
