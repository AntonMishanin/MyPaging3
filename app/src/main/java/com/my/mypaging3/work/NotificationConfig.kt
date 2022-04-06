package com.my.mypaging3.work

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.WorkManager
import com.my.mypaging3.R
import java.util.*

class NotificationConfig(
    private val context: Context
) {

    private val id = (0..10_000).random()
    private val notificationCompat = NotificationManagerCompat.from(context)

    fun createNotification(id: UUID): Notification {
        val channelId = "getString(R.string.notification_channel_id)"
        val title =" getString(R.string.notification_title)"
        val cancel = "getString(R.string.cancel_processing)"
        val name = "getString(R.string.channel_name)"
        // This PendingIntent can be used to cancel the Worker.
        val intent = WorkManager.getInstance(context).createCancelPendingIntent(id)

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setTicker(title)
            .setSmallIcon(0)
            .setOngoing(true)
            .addAction(0, cancel, intent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(channelId, name).also {
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

    fun makeNotification(message: String, id: UUID): Notification {

        val channelId = "CHANNEL_ID"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "notification name"
            val description = "notification description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }

      //  val intent = WorkManager.getInstance(context).createCancelPendingIntent(id)

        return NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Notification title")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(LongArray(0))
           // .addAction(0, "cancel", intent)
            .build()
    }

    fun showNotification(message: String, requestId: UUID) =
        notificationCompat.notify(id, makeNotification(message, requestId))

    fun cancelNotification() = notificationCompat.cancel(id)

    fun getNotificationId() = id
}