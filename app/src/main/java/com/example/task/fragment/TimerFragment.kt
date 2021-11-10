package com.example.task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.task.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {
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
                val combinedTime=(timeMinutes*60)+timeSeconds
            }
            launchTimer.setOnClickListener {
                // Todo launch timer
            }
        }

    }
}