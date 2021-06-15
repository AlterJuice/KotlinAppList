package com.example.kotlinapplist

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.example.kotlinapplist.data.Consts

class ItemService : Service() {

    private val importance = NotificationManager.IMPORTANCE_HIGH

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotification()
        return START_STICKY
    }

    private fun createNotification(){
        val channel = NotificationChannel(Consts.CHANNEL_ID, Consts.CHANNEL_NAME, importance).apply {
            description = Consts.CHANNEL_DESCRIPTION
        }
        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        val notification: Notification.Builder = Notification.Builder(this, Consts.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_round_email_24)
            .setContentTitle("Item notification")
            .setContentText("Do u want to see last item?")
            .setAutoCancel(true)
            .setContentIntent(PendingIntent.getBroadcast(this, 0, getBroadcastIntent(), PendingIntent.FLAG_UPDATE_CURRENT))
        startForeground(Consts.notificationID, notification.build())
    }
    private fun getBroadcastIntent(): Intent {
        return Intent("${Consts.APP_PACKAGE}.${Consts.ACTION_NOTIF_CLICKED}").apply {
            setPackage(Consts.APP_PACKAGE)
        }
    }
}