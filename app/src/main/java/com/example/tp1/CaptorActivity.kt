package com.example.tp1

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tp1.databinding.ActivityCaptorBinding

class CaptorActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sm: SensorManager
    private lateinit var binding: ActivityCaptorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCaptorBinding.inflate(layoutInflater)
        sm = getSystemService(SENSOR_SERVICE) as SensorManager
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val mMagneticField = sm.getDefaultSensor(
            Sensor.TYPE_MAGNETIC_FIELD
        )
        sm.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
    }

    override fun onStop() {
        super.onStop()
        sm.unregisterListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD));
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val sensor: Int = event!!.sensor.type
        val values: FloatArray = event.values

        synchronized(this) {
            if (sensor == Sensor.TYPE_MAGNETIC_FIELD) {
                val (magField_x, magField_y, magField_z) = values
                binding.x.text = "$magField_x"
                binding.y.text = "$magField_y"
                binding.z.text = "$magField_z"
                Log.d("INFO", "$magField_x : $magField_y : $magField_z")
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}