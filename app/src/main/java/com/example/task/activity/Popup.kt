package com.example.task.activity

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import com.example.task.databinding.PopUpTimerBinding

class Popup : Activity() {
    lateinit var binding: PopUpTimerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PopUpTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL.toInt(),
            WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL.toInt()
        )

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        window.setLayout((width * 0.95).toInt(), (height * 0.18).toInt())
        window.setGravity(Gravity.TOP)
        binding.ok.setOnClickListener {
            finish()
        }
    }
}