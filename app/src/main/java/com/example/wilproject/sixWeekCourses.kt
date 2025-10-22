package com.example.wilproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class sixWeekCourses : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixweekcourse) // Fixed layout name

        // Initialize views
        val childMindingButton = findViewById<Button>(R.id.childMindingButton)
        val cookingButton = findViewById<Button>(R.id.cookingButton)
        val gardenMaintenanceButton = findViewById<Button>(R.id.gardenMaintenanceButton)
        val contactButton = findViewById<ImageButton>(R.id.contactButton)
        val backButton = findViewById<ImageButton>(R.id.backButton)

        // Set click listeners
        setupClickListeners(
            childMindingButton,
            cookingButton,
            gardenMaintenanceButton,
            contactButton,
            backButton
        )
    }

    private fun setupClickListeners(
        childMindingButton: Button,
        cookingButton: Button,
        gardenMaintenanceButton: Button,
        contactButton: ImageButton,
        backButton: ImageButton
    ) {
        // Navigate to First Aid course details
        // Navigate to Child Minding course details
        childMindingButton.setOnClickListener {
            val intent = Intent(this, childMinding::class.java)
            intent.putExtra("course_name", "Child Minding")
            intent.putExtra("course_type", "six_week")
            intent.putExtra("course_fee", "R800")
            intent.putExtra("course_purpose", "To provide safe and educational child care services")
            intent.putExtra("course_content", arrayOf(
                "Child safety and first aid",
                "Age-appropriate activities",
                "Nutrition for children",
                "Behavior management",
                "Developmental milestones"
            ))
            startActivity(intent)
        }

// Navigate to Cooking course details
        cookingButton.setOnClickListener {
            val intent = Intent(this, cooking::class.java)
            intent.putExtra("course_name", "Cooking")
            intent.putExtra("course_type", "six_week")
            intent.putExtra("course_fee", "R800")
            intent.putExtra("course_purpose", "To provide basic cooking and food preparation skills")
            intent.putExtra("course_content", arrayOf(
                "Basic cooking techniques",
                "Food safety and hygiene",
                "Meal planning and preparation",
                "Nutrition basics",
                "Kitchen equipment usage"
            ))
            startActivity(intent)
        }

// Navigate to Garden Maintenance course details
        gardenMaintenanceButton.setOnClickListener {
            val intent = Intent(this, garden::class.java)
            intent.putExtra("course_name", "Garden Maintenance")
            intent.putExtra("course_type", "six_week")
            intent.putExtra("course_fee", "R800")
            intent.putExtra("course_purpose", "To provide garden upkeep and maintenance skills")
            intent.putExtra("course_content", arrayOf(
                "Lawn care and mowing",
                "Pruning and weeding",
                "Plant care and watering",
                "Basic garden tools usage",
                "Seasonal garden maintenance"
            ))
            startActivity(intent)
        }

        // Navigate to Contact Details screen
        contactButton.setOnClickListener {
            val intent = Intent(this, contactDetails::class.java)
            startActivity(intent)
        }

        // Back button - return to MainActivity
        backButton.setOnClickListener {
            finish() // This will close current activity and return to previous one
        }
    }

    override fun onResume() {
        super.onResume()
        // Any additional setup when returning to this screen
    }
}