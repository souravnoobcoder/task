package com.example.task.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.task.databinding.FragmentTimerBinding
import com.example.task.service.TimerService

class TimerFragment : Fragment() {
    private var combinedTime : Long?=null
    lateinit var binding: FragmentTimerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentTimerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setTimer.setOnClickListener {
               val timeMinutes= minutes.editableText.toString().toInt()
                val timeSeconds = seconds.editableText.toString().toInt()
                combinedTime=((timeMinutes*60)+timeSeconds).toLong()
            }
            launchTimer.setOnClickListener {
                if (combinedTime==null)
                    Toast.makeText(activity, "Set timer first", Toast.LENGTH_SHORT).show()
                else{
                    val timerIntent= Intent(context, TimerService::class.java)
                    timerIntent.putExtra("a",combinedTime)
                    activity?.startService(timerIntent)
                }
            }
        }

    }

}