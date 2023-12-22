package com.dcu.a2p2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class SecondActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun save(view: View) {
        val emailData = EmailData(
            findViewById<EditText>(R.id.edit_message_to).text.toString(),
            findViewById<EditText>(R.id.edit_message_subject).text.toString(),
            findViewById<EditText>(R.id.edit_message_compose).text.toString()
        )
        setResult(RESULT_OK, intent.putExtra("emailData", emailData))
        finish()
    }
}