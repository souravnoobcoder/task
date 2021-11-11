package com.example.task.activity

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import com.example.task.databinding.PopUpTimerBinding

class Popup : Activity() {
    lateinit var binding: PopUpTimerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PopUpTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        window.setLayout((width * 0.8).toInt(), (height * 0.3).toInt())
        window.setGravity(Gravity.TOP)
        binding.ok.setOnClickListener {
            finish()
        }
    }
}