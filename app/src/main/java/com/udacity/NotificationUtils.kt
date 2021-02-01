package com.udacity

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat

val NOTIFICATION_ID = 0

fun NotificationManager.sendNotification(
    statusValue: Int,
    colorValue: Int,
    messageValue: Int,
    applicationContext: Context) {

    val contentIntent = Intent(applicationContext, DetailActivity::class.java)
    contentIntent.putExtra(
        applicationContext.getString(R.string.status_id),
        statusValue)

    contentIntent.putExtra(
        applicationContext.getString(R.string.color_id),
        colorValue)

    contentIntent.putExtra(
        applicationContext.getString(R.string.message_id),
        messageValue)


    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val style = NotificationCompat.InboxStyle()

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.channel_id)
    )
        .setSmallIcon(R.drawable.ic_assistant_black_24dp)
        .setContentIntent(contentPendingIntent)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(applicationContext.getString(R.string.notification_description))
        .setStyle(style)
        .addAction(
            0,
            applicationContext.getString(R.string.notification_button),
            contentPendingIntent
        )
        .setPriority(NotificationCompat.PRIORITY_HIGH)
//        .setAutoCancel(true)

    notify(NOTIFICATION_ID, builder.build())
}