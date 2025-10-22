package com.example.wilproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class sixMonthCourses : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixmonthcourse) // Fixed layout name

        // Initialize views
        val firstAidButton = findViewById<Button>(R.id.firstAidButton)
        val sewingButton = findViewById<Button>(R.id.sewingButton)
        val landscapingButton = findViewById<Button>(R.id.landscapingButton)
        val lifeSkillsButton = findViewById<Button>(R.id.lifeSkillsButton)
        val contactButton = findViewById<ImageButton>(R.id.contactButton)
        val backButton = findViewById<ImageButton>(R.id.backButton)

        // Set click listeners
        setupClickListeners(
            firstAidButton,
            sewingButton,
            landscapingButton,
            lifeSkillsButton,
            contactButton,
            backButton
        )
    }

    private fun setupClickListeners(
        firstAidButton: Button,
        sewingButton: Button,
        landscapingButton: Button,
        lifeSkillsButton: Button,
        contactButton: ImageButton,
        backButton: ImageButton
    ) {
        // Navigate to First Aid course details
        firstAidButton.setOnClickListener {
            val intent = Intent(this, firstAid::class.java)
            intent.putExtra("course_name", "First Aid")
            intent.putExtra("course_type", "six_month")
            intent.putExtra("course_fee", "R1500")
            intent.putExtra("course_purpose", "To provide first aid awareness and basic life support")
            intent.putExtra("course_content", arrayOf(
                "Wounds and bleeding",
                "Burns and fractures",
                "Emergency scene management",
                "Cardio-Pulmonary Resuscitation (CPR)",
                "Respiratory distress e.g., Choking, blocked airway"
            ))
            startActivity(intent)
        }

        // Navigate to Sewing course details
        sewingButton.setOnClickListener {
            val intent = Intent(this, sewing::class.java)
            intent.putExtra("course_name", "Sewing")
            intent.putExtra("course_type", "six_month")
            intent.putExtra("course_fee", "R1500")
            intent.putExtra("course_purpose", "To provide alterations and new garment tailoring services")
            intent.putExtra("course_content", arrayOf(
                "Types of stitches",
                "Threading a sewing machine",
                "Sewing buttons, zips, hems and seams",
                "Alterations",
                "Designing and sewing new garments"
            ))
            startActivity(intent)
        }

        // Navigate to Landscaping course details
        landscapingButton.setOnClickListener {
            val intent = Intent(this, landscaping::class.java)
            intent.putExtra("course_name", "Landscaping")
            intent.putExtra("course_type", "six_month")
            intent.putExtra("course_fee", "R1500")
            intent.putExtra("course_purpose", "To provide landscaping services for new and established gardens")
            intent.putExtra("course_content", arrayOf(
                "Indigenous and exotic plants and trees",
                "Fixed structures (fountains, statues, benches, tables, built-in braai)",
                "Balancing of plants and trees in a garden",
                "Aesthetics of plant shapes and colors",
                "Garden layout"
            ))
            startActivity(intent)
        }

        // Navigate to Life Skills course details
        lifeSkillsButton.setOnClickListener {
            val intent = Intent(this, lifeSkills::class.java)
            intent.putExtra("course_name", "Life Skills")
            intent.putExtra("course_type", "six_month")
            intent.putExtra("course_fee", "R1500")
            intent.putExtra("course_purpose", "To provide skills to navigate basic life necessities")
            intent.putExtra("course_content", arrayOf(
                "Opening a bank account",
                "Basic labour law (know your rights)",
                "Basic reading and writing literacy",
                "Basic numeric literacy"
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