package com.example.tp1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class CameraActivity : AppCompatActivity() {
    private val CAMERA_REQUEST = 1888
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private val MY_CAMERA_PERMISSION_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        textView = findViewById<View>(R.id.textView) as TextView
        textView.text = ""
        imageView = findViewById<View>(R.id.imageView) as ImageView
        val photoButton: Button = findViewById<View>(R.id.button) as Button
    }

    fun onClick(v: View?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
            } else {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show()
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            var countRedPixel = 0
            val photo = data?.extras!!["data"] as Bitmap?
            if (photo != null) {
                for(x in 0 until photo.width) {
                    for(y in 0 until photo.height) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            val color = photo.getColor(x, y)
                            if(color.red() > color.green()) {
                                countRedPixel++
                            }
                        }
                    }
                }
            }
            textView.text = "$countRedPixel"
            imageView.setImageBitmap(photo)
        }
    }
}