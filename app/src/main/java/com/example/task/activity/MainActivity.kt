package com.example.task.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.task.R
import com.example.task.databinding.ActivityMainBinding
import com.example.task.fragment.AlarmFragment
import com.example.task.fragment.TimerFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainerViewTag.id,TimerFragment::class.java,null)
            .commit()
        binding.bottomNavigation.setOnItemSelectedListener {
            val itemId = it.itemId
            if (itemId == R.id.alarm) {
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainerViewTag.id,AlarmFragment::class.java,null)
                    .commit()
            }
            if (itemId == R.id.timer) {
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainerViewTag.id, TimerFragment::class.java,null)
                    .commit()
            }
            return@setOnItemSelectedListener true
        }
    }

}