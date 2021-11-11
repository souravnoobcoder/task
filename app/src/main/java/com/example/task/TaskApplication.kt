package com.example.task

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AlertDialog

class TaskApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        notificationCreator()
        IntentFilter(Intent.ACTION_BOOT_COMPLETED).also {
            registerReceiver(bootsUp,it)
        }
    }
    private val bootsUp=object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            displayAlert()
        }

    }
    private fun notificationCreator(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelID,
            channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.description="Timer is running"
            val notificationManager=getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object{
        const val channelID="channel1"
        const val channelName="timer and alarm"
    }

     fun displayAlert() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to exit?").setCancelable(
            false
        ).setPositiveButton("Yes"
        ) { dialog, _ -> dialog.cancel() }
            .setNegativeButton("No"
            ) { dialog, _ -> dialog.cancel() }
        val alert: AlertDialog = builder.create()
        alert.show()
    }
}