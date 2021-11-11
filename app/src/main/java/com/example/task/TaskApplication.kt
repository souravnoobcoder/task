package com.example.task

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class TaskApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        notificationCreator()
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
}