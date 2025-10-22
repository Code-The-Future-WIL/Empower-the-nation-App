package com.example.wilproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class garden : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garden) // Added missing parenthesis

        // Initialize views - CORRECTED IDs to match XML
        val courseNameText = findViewById<TextView>(R.id.textBoxOne)
        val feesText = findViewById<TextView>(R.id.feesText)
        val feeAmountText = findViewById<TextView>(R.id.textBoxTwo)
        val purposeText = findViewById<TextView>(R.id.purposeText)
        val purposeContentText = findViewById<TextView>(R.id.textBoxThree)
        val contentText = findViewById<TextView>(R.id.contentText)
        val contentDetailsText = findViewById<TextView>(R.id.textBoxFour)
        val contactButton = findViewById<ImageButton>(R.id.contactButton)
        val backButton = findViewById<ImageButton>(R.id.backButton)
        val calculateFeesButton = findViewById<Button>(R.id.calculateFeesButton)

        // Get course data from intent
        val courseName = intent.getStringExtra("course_name") ?: "Garden Maintenance"
        val courseType = intent.getStringExtra("course_type") ?: "six_week"
        val courseFee = intent.getStringExtra("course_fee") ?: "R750"
        val coursePurpose = intent.getStringExtra("course_purpose") ?: "To provide basic knowledge of watering, pruning and planting in a domestic garden."
        val courseContent = intent.getStringArrayExtra("course_content") ?: arrayOf(
            "Water restrictions and the watering requirements of indigenous plants",
            "Pruning and propagation of plants",
            "Planting techniques for different plant types"
        )

        // Populate views with course data
        populateCourseDetails(
            courseNameText,
            feeAmountText,
            purposeContentText,
            contentDetailsText,
            courseName,
            courseFee,
            coursePurpose,
            courseContent
        )

        // Set background colors based on course
        setCourseColors(courseNameText, feeAmountText, purposeContentText, contentDetailsText, courseName)

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
            else -> "#bfecac" // Default to Garden Maintenance color
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
        // Any additional setup when returning to this screen
    }
}