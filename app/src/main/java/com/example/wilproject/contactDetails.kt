package com.example.wilproject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class contactDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactdetails)

        // Initialize back button
        val backButton = findViewById<ImageButton>(R.id.backButton)

        // Set click listener for back button
        backButton.setOnClickListener {
            finish() // Close this activity and return to previous one
        }
    }

    override fun onResume() {
        super.onResume()
        // Any additional setup when returning to this screen
    }
}