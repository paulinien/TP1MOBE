package com.example.tp1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.tp1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), Runnable {

    private lateinit var binding: ActivityMainBinding
    private var mHandler: Handler = Handler()
    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.buttonNext.setOnClickListener(::onClickCommencerButton)
        binding.buttonCamera.setOnClickListener(::onClickCameraButton)
        binding.img.setImageDrawable(getDrawable(R.drawable.first_img))
        setContentView(binding.root)
        run()
    }

    fun onClickCommencerButton(v: View?) {
        switchActivity(CaptorActivity::class.java)
    }

    fun onClickCameraButton(v: View?) {
        switchActivity(CameraActivity::class.java)
    }

    fun switchActivity(c : Class<out AppCompatActivity>) {
        val intent = Intent(this, c)
        startActivity(intent)
        finishActivity(0)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun run() {
        if((++i)%2 == 0) {
            binding.img.setImageDrawable(getDrawable(R.drawable.first_img))
        } else {
            binding.img.setImageDrawable(getDrawable(R.drawable.second_img))
        }
        mHandler.postDelayed(this, 1000)
    }

}