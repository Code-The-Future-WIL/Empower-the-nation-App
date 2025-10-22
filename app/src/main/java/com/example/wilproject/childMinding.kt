package com.example.wilproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class childMinding : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_childminding)

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
        val colorString = when (courseName) {
            "First Aid" -> "#ff7d7d"
            "Sewing" -> "#9bd4e4"
            "Landscaping" -> "#c4a092"
            "Life Skills" -> "#fff8bd"
            "Child Minding" -> "#f6c6fa"
            "Cooking" -> "#ff99d8"
            "Garden Maintenance" -> "#bfecac"
            else -> "#c4a092"
        }

        val color = android.graphics.Color.parseColor(colorString)

        // Set background colors for all text boxes
        nameText.setBackgroundColor(color)
        feeText.setBackgroundColor(color)
        purposeText.setBackgroundColor(color)
        contentText.setBackgroundColor(color)
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

    }
}