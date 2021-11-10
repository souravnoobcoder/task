package com.example.task.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.task.R.drawable
import com.example.task.TaskApplication
import com.example.task.activity.MainActivity


class TimerService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val time= (intent?.getIntExtra("a",0)?.toLong())?.times(1000)
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent, 0
        )
            val countDownTimer=object : CountDownTimer(time!!,1000){
                override fun onTick(millisUntilFinished: Long) {
                    TODO("Not yet implemented")
                }

                override fun onFinish() {
                    TODO("Not yet implemented")
                }

            }
            val myNotification = NotificationCompat.Builder(this, TaskApplication.channelID)
            .setContentTitle("Foreground service")
            .setContentText("Timer sets of time $time")
            .setSmallIcon(drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
            startForeground(1,myNotification)
        return START_NOT_STICKY

    }

    override fun onBind(intent: Intent?): IBinder? {
       return null
    }
}