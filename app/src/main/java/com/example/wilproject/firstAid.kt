package com.example.wilproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class firstAid : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firstaid)

        // Initialize views
        val courseNameText = findViewById<TextView>(R.id.textBoxOne)
        val feesText = findViewById<TextView>(R.id.textBoxTwo)
        val purposeText = findViewById<TextView>(R.id.textBoxThree)
        val contentText = findViewById<TextView>(R.id.textBoxFour)
        val contactButton = findViewById<ImageButton>(R.id.contactButton)
        val backButton = findViewById<ImageButton>(R.id.backButton)
        val calculateFeesButton = findViewById<Button>(R.id.calculateFeesButton)

        // Get course data from intent
        val courseName = intent.getStringExtra("course_name") ?: "Course"
        val courseType = intent.getStringExtra("course_type") ?: "six_month"
        val courseFee = intent.getStringExtra("course_fee") ?: "R1500"
        val coursePurpose = intent.getStringExtra("course_purpose") ?: "Course purpose"
        val courseContent = intent.getStringArrayExtra("course_content") ?: arrayOf("Course content")

        // Populate views with course data
        populateCourseDetails(
            courseNameText,
            feesText,
            purposeText,
            contentText,
            courseName,
            courseFee,
            coursePurpose,
            courseContent
        )

        // Set background colors based on course
        setCourseColors(courseNameText, feesText, purposeText, contentText, courseName)

        // Set click listeners
        setupClickListeners(contactButton, backButton, calculateFeesButton)
    }

    private fun populateCourseDetails(
        nameText: TextView,
        feeText: TextView,
        purposeText: TextView,
        contentText: TextView,
        name: String,
        fee: String,
        purpose: String,
        content: Array<String>
    ) {
        nameText.text = name
        feeText.text = fee
        purposeText.text = purpose

        // Format content as numbered list
        val contentBuilder = StringBuilder()
        content.forEachIndexed { index, item ->
            contentBuilder.append("${index + 1}. $item")
            if (index < content.size - 1) {
                contentBuilder.append("\n")
            }
        }
        contentText.text = contentBuilder.toString()
    }

    private fun setCourseColors(
        nameText: TextView,
        feeText: TextView,
        purposeText: TextView,
        contentText: TextView,
        courseName: String
    ) {
        val colorResId = when (courseName) {
            "First Aid" -> android.R.color.holo_red_light // #ff7d7d equivalent
            "Sewing" -> android.R.color.holo_blue_light // #9bd4e4 equivalent
            "Landscaping" -> android.R.color.darker_gray // #c4a092 equivalent
            "Life Skills" -> android.R.color.holo_orange_light // #fff8bd equivalent
            "Child Minding" -> android.R.color.holo_purple // #f6c6fa equivalent
            "Cooking" -> android.R.color.holo_red_light // #ff99d8 equivalent
            "Garden Maintenance" -> android.R.color.holo_green_light // #bfecac equivalent
            else -> android.R.color.darker_gray
        }

        // Set background colors for all text boxes
        nameText.setBackgroundColor(getColor(colorResId))
        feeText.setBackgroundColor(getColor(colorResId))
        purposeText.setBackgroundColor(getColor(colorResId))
        contentText.setBackgroundColor(getColor(colorResId))
    }

    private fun setupClickListeners(
        contactButton: ImageButton,
        backButton: ImageButton,
        calculateFeesButton: Button
    ) {
        // Navigate to Contact Details screen
        contactButton.setOnClickListener {
            val intent = Intent(this, contactDetails::class.java)
            startActivity(intent)
        }

        // Back button - return to previous screen
        backButton.setOnClickListener {
            finish()
        }

        // Navigate to Calculate Fees screen
        calculateFeesButton.setOnClickListener {
            val intent = Intent(this, calculate::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Any additional setup when returning to this screen
    }
}