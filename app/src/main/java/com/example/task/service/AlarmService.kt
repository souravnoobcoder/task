package com.example.task.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.example.task.activity.MainActivity
import com.example.task.activity.Popup
import java.util.*

class AlarmService : Service() {

    private var calendarTime :Long?=null
    private var alarmManager : AlarmManager?=null
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        alarmManager=applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        calendarTime=intent?.getLongExtra("b",0)
        val alarmIntent=Intent(this, Popup::class.java)
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val pendingIntent = PendingIntent.getActivity(this, 0, alarmIntent, 0)
        alarmManager?.setInexactRepeating(AlarmManager.RTC,calendarTime!!,
        AlarmManager.INTERVAL_DAY,pendingIntent)
        return super.onStartCommand(intent, flags, startId)
    }
}