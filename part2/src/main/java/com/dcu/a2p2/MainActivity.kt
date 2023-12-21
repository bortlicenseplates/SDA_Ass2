package com.dcu.a2p2

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val REQUEST_IMAGE_CAPTURE = 1

    fun dispatchCameraIntent(view: View) {
        try {
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
            Log.e("Activity Camera fail", e.message.toString())
        }
    }
    fun readPictureIntent() {
        try {
            startActivityForResult(Intent(), REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
            Log.e("Activity Camera fail", e.message.toString())
        }
    }
}