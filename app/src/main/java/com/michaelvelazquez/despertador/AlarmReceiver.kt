package com.michaelvelazquez.despertador

import android.Manifest
import android.app.Activity
import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return

        val i = Intent(context, DestinationActivity::class.java )
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivities(context, 0, arrayOf(i),0)

        val builder = NotificationCompat.Builder(context, "MICHAEL")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("ALARMA DESPERTADORA")
            .setContentText("ES HORA DE IR A ALA UNIVERSIDAD")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager =  NotificationManagerCompat.from(context)

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_BOOT_COMPLETED)
            == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(123, builder)
        } else {
            if (context is Activity) {
                ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.RECEIVE_BOOT_COMPLETED), 1)
            }
        }
    }
}




