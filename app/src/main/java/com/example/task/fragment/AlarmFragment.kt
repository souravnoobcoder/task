package com.example.task.fragment

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.task.databinding.FragmentAlarmBinding
import com.example.task.service.AlarmService
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AlarmFragment : Fragment() {


    private var alarmManager: AlarmManager? = null

    private var timePicker: MaterialTimePicker? = null
    private var calender: Calendar? = null
    private lateinit var binding: FragmentAlarmBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlarmBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        binding.apply {
            createAlarm.setOnClickListener {
                createTimerPicker()
                timePicker?.addOnPositiveButtonClickListener {
                    if (timePicker?.hour!! < 12)
                        timeSet.text = " ${timePicker?.hour} : ${timePicker?.minute} AM"
                    else
                        timeSet.text = " ${timePicker?.hour!! - 12} : ${timePicker?.minute} PM"

                    calender = Calendar.getInstance()
                    calender?.apply {
                        set(Calendar.HOUR_OF_DAY, timePicker?.hour!!)
                        set(Calendar.MINUTE, timePicker?.minute!!)
                        set(Calendar.SECOND, 0)
                        set(Calendar.MILLISECOND, 0)
                    }
                }
            }
            launchAlarm.setOnClickListener {
                if (calender == null)
                    Toast.makeText(context, "time not set yet", Toast.LENGTH_SHORT).show()
                else {
                    val alarmService = Intent(context, AlarmService::class.java)
                    alarmService.putExtra("b", calender?.timeInMillis)
                    requireContext().startService(alarmService)
                }
            }
        }
    }

    private fun createTimerPicker() {
        timePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .setTitleText("Select Appointment time")
                .build()
        timePicker!!.show(parentFragmentManager, "dk")
    }
}