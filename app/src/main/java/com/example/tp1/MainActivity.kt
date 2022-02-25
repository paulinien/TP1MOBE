package com.example.tp1

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tp1.databinding.ActivityMainBinding
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.button.setOnClickListener(::onClickListener)
//        tvCounter.text = "${counter}"
        setContentView(binding.root)
//        mHandler.postDelayed(mUpdateTimeTask, 10000);
    }

    fun onClickListener(v: View?) {

    }
    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

}