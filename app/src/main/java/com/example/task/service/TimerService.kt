package com.example.task.service

import android.app.PendingIntent
import android.app.Service
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import com.example.task.R
import com.example.task.R.drawable
import com.example.task.TaskApplication
import com.example.task.activity.MainActivity
import com.example.task.activity.Popup


class TimerService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val time: Long? = (intent?.getLongExtra("a", 0))?.times(1000)
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent, 0
        )
        val countDownTimer = object : CountDownTimer(time!!, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onFinish() {
//                val timerIntent = Intent(this@TimerService, Popup::class.java)
//                timerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(timerIntent)
                showAlertDialog()
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun showAlertDialog() {
        val dialog = AlertDialog.Builder(this,R.style.Theme)
            .setTitle("hello")
            .setMessage("kdfkdfkd")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.cancel()
            }.setIcon(drawable.ic_alarm_)
            .create()
        dialog.window?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
            dialog.show()
    }
}