package com.dcu.a2p1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.dcu.a2p1.R

class MainActivity : Activity() {
//  Kotlin companion object acts as static members. @JvmField declares val as public final
//  Original Java: public final static String EXTRA_MESSAGE = "com.dcu.a2p1.MESSAGE";
    companion object {
        @JvmField val EXTRA_MESSAGE: String = "com.dcu.a2p1.MESSAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendMessage(view: View) {
        val intent = Intent(this, DisplayMessageActivity::class.java)
        val editText = findViewById<View>(R.id.edit_message) as EditText
        val message = editText.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivity(intent)
    }
}
