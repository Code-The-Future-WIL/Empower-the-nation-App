package com.example.wilproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        val sixMonthButton = findViewById<Button>(R.id.sixMonthButton)
        val sixWeekButton = findViewById<Button>(R.id.sixWeekButton)
        val contactButton = findViewById<ImageButton>(R.id.contactButton)

        // Set click listeners
        setupClickListeners(sixMonthButton, sixWeekButton, contactButton)
    }

    private fun setupClickListeners(
        sixMonthButton: Button,
        sixWeekButton: Button,
        contactButton: ImageButton
    ) {
        // Navigate to Six-month courses screen
        sixMonthButton.setOnClickListener {
            val intent = Intent(this, sixMonthCourses::class.java)
            startActivity(intent)
        }

        // Navigate to Six-week courses screen
        sixWeekButton.setOnClickListener {
            val intent = Intent(this, sixWeekCourses::class.java)
            startActivity(intent)
        }

        // Navigate to Contact Details screen
        contactButton.setOnClickListener {
            val intent = Intent(this, contactDetails::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Any additional setup when returning to this screen
    }
}