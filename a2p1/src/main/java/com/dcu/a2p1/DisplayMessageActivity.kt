package com.dcu.a2p1

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.core.app.NavUtils


class DisplayMessageActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the message from the intent
        val intent = intent
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)

        // Create the text view
        val textView = TextView(this)
        textView.textSize = 40f
        textView.text = message

        // Set the text view as the activity layout
        setContentView(textView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}