package com.example.task.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.view.View
import android.view.WindowManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.task.R.drawable
import com.example.task.TaskApplication
import com.example.task.activity.MainActivity
import com.example.task.activity.Popup


class TimerService : Service() {
    private var windowManager: WindowManager? = null
    private var popupView: View? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val time: Long? = (intent?.getLongExtra("a", 0))?.times(1000)
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent, 0
        )
        val timerFinish = NotificationCompat.Builder(this, TaskApplication.channelID)
            .setContentTitle("Timer finished")
            .setContentText("Your set timer finished")
            .setSmallIcon(drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .build()
        val countDownTimer = object : CountDownTimer(time!!, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }


            override fun onFinish() {
                val inten=Intent(this@TimerService, Popup::class.java)
                inten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(inten)
//                with(NotificationManagerCompat.from(applicationContext)) {
//                    notify(2, timerFinish)
//                }
                stopSelf()
            }
        }
        countDownTimer.start()
        val myNotification =
            NotificationCompat.Builder(applicationContext, TaskApplication.channelID)
                .setContentTitle("Timer")
                .setContentText("Timer is Running")
                .setSmallIcon(drawable.ic_launcher_foreground)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setContentIntent(pendingIntent)
                .build()
        startForeground(1, myNotification)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}