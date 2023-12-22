package com.dcu.a2p2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.TakePicture
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var emailData: EmailData? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.send_button).setEnabled(false);
    }

    private var startCamera =
        registerForActivityResult(TakePicture()) { result ->
            Log.i(null, "result: $result")
             if (result) {
                Log.i(null, "YEAH!")
            }
        }

    private val startGallery =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> Log.i("debug", "okay")
                Activity.RESULT_CANCELED -> Log.i("debug", "not okay")
            }
        }

    private val startSecondActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    val intent = result.data
                    if (intent == null) {
                        Log.i("debug", "intent is null")
                        return@registerForActivityResult
                    }
                    @Suppress("DEPRECATION")
                    emailData = when (Build.VERSION.SDK_INT) {
                        Build.VERSION_CODES.TIRAMISU -> intent.getSerializableExtra("emailData", EmailData::class.java)
                        else -> intent.getSerializableExtra("emailData") as EmailData
                    }?: return@registerForActivityResult

                    val blankActivityTextView = findViewById<TextView>(R.id.blank_intent_view)
                    findViewById<Button>(R.id.send_button).isEnabled = true;
                    // set text of blank activity textview
                    blankActivityTextView.text = buildString {
                        append("To: ")
                        append(emailData!!.to)
                        append("\nSubject: ")
                        append(emailData!!.subject)
                        append("\nCompose: ")
                        append(emailData!!.compose)
                    }
                }
                Activity.RESULT_CANCELED -> {
                    findViewById<Button>(R.id.send_button).isEnabled = false;
                }
            }
        }

    fun dispatchCameraIntent(view: View) {
        val imageUri = Uri.parse("android.resource://com.dcu.a2p2/drawable/ic_launcher_background")
        startCamera.launch(imageUri)
    }

    fun dispatchGalleryIntent(view: View) {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        )
        startGallery.launch(galleryIntent)
    }

    fun callActivity(view: View) {
        val secondActivityIntent = Intent(this, SecondActivity::class.java)
        startSecondActivity.launch(secondActivityIntent)
    }

    fun composeEmail(view: View) {
        val emailData = emailData ?: return
        val selectorIntent = Intent(Intent.ACTION_SENDTO)
        selectorIntent.data = Uri.parse("mailto:")

        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailData.to))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailData.subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailData.compose)
        emailIntent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("message/rfc822"))
        emailIntent.selector = selectorIntent
        Log.i(null,"emailIntent: $emailIntent")
        try {
            Log.i(null,"emailIntent: $emailIntent")
            startActivity(Intent.createChooser(emailIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            Log.i(null,"emailIntent: $emailIntent")
        }
    }
}
