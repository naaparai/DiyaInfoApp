/*
 * Copyright (c) 2020. Kunsang Wangyal
 */

package com.example.diyainfoapp.global

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.diyainfoapp.MainActivity
import com.example.diyainfoapp.R
import com.example.diyainfoapp.extension.getNotificationChannelId

const val NOTIFICATION_ID = 1001

fun createNotificationChannel(
    context: Context,
    importance: Int,
    showBadge: Boolean,
    name: String,
    description: String
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = context.getNotificationChannelId()
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = description
        channel.setShowBadge(showBadge)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.createNotificationChannel(channel)
    }
}

fun showNotification(
    context: Context,
    title: String,
    desc: String,
    deepLink: String,
    notificationId: Int = NOTIFICATION_ID
) {
    val channelId = "${context.packageName}-${context.getString(R.string.app_name)}"
    val notificationIntent = Intent(context, MainActivity::class.java)
    notificationIntent.data = Uri.parse(deepLink)
    val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    val notificationBuilder = NotificationCompat.Builder(context, channelId).apply {
        setSmallIcon(R.drawable.ic_notification)
        setContentTitle(title)
        setContentText(desc)
        setVibrate(longArrayOf(1))
        setAutoCancel(true)
        setSound(uri)
        setDefaults(NotificationCompat.DEFAULT_ALL)
        setStyle(NotificationCompat.BigTextStyle().bigText(desc))
        notification.flags = Notification.FLAG_ONGOING_EVENT or Notification.FLAG_NO_CLEAR
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
    }
    val notificationManager = NotificationManagerCompat.from(context)
    notificationManager.notify(notificationId, notificationBuilder.build())
}

fun cancelNotification(context: Context, notificationId: Int) =
    NotificationManagerCompat.from(context).cancel(notificationId)
