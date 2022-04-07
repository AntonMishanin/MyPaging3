package com.my.mypaging3.work

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*
import android.media.RingtoneManager
import android.app.PendingIntent
import android.content.Intent
import com.my.mypaging3.R

class NotificationConfig(
    private val context: Context
) {

    private val id = (0..10_000).random()
    private val notificationCompat = NotificationManagerCompat.from(context)

    fun createNotification(id: UUID): Notification {
        val channelId = "getString(R.string.notification_channel_id)"
        val title = " getString(R.string.notification_title)"
        val cancel = "getString(R.string.cancel_processing)"
        val name = "getString(R.string.channel_name)"

        val intentAction = Intent(context, NotificationActions::class.java)
        intentAction.putExtra("action", "actionName")
        val intentLogin = PendingIntent.getBroadcast(context, 1, intentAction, PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setTicker(title)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)
            .addAction(R.drawable.ic_launcher_background, "No", intentLogin)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.RED, 3000, 3000)
            .setSound(alarmSound)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(channelId, name).also {
                it.enableLights(true)
                it.lightColor = Color.RED
                it.enableVibration(true)
                it.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
                builder.setChannelId(it.id)
            }
        }
        return builder.build()
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(
        channelId: String,
        name: String
    ): NotificationChannel {
        return NotificationChannel(
            channelId, name, NotificationManager.IMPORTANCE_LOW
        ).also { channel ->
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showNotification(message: String, requestId: UUID) =
        notificationCompat.notify(id, createNotification(requestId))

    fun cancelNotification() = notificationCompat.cancel(id)

    fun getNotificationId() = id
}