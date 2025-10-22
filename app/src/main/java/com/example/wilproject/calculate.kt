package com.example.wilproject

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class calculate : AppCompatActivity() {

    private lateinit var fullNameInput: EditText
    private lateinit var phoneNumberInput: EditText
    private lateinit var emailAddressInput: EditText

    private lateinit var checkboxFirstAid: CheckBox
    private lateinit var checkboxSewing: CheckBox
    private lateinit var checkboxLandscaping: CheckBox
    private lateinit var checkboxLifeSkills: CheckBox
    private lateinit var checkboxChildMinding: CheckBox
    private lateinit var checkboxCooking: CheckBox
    private lateinit var checkboxGardenMaintenance: CheckBox

    private lateinit var calculateTotalButton: Button
    private lateinit var totalFeesText: TextView
    private lateinit var contactButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var requestConsultationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate)

        initializeViews()
        setupClickListeners()
        // Initialize with R0 - no automatic calculation
        totalFeesText.text = "R0"
    }

    private fun initializeViews() {
        // Input fields
        fullNameInput = findViewById(R.id.fullName)
        phoneNumberInput = findViewById(R.id.phoneNumber)
        emailAddressInput = findViewById(R.id.emailAddress)

        // Checkboxes
        checkboxFirstAid = findViewById(R.id.checkboxFirstAid)
        checkboxSewing = findViewById(R.id.checkboxSewing)
        checkboxLandscaping = findViewById(R.id.checkboxLandscaping)
        checkboxLifeSkills = findViewById(R.id.checkboxLifeSkills)
        checkboxChildMinding = findViewById(R.id.checkboxChildMinding)
        checkboxCooking = findViewById(R.id.checkboxCooking)
        checkboxGardenMaintenance = findViewById(R.id.checkboxGardenMaintenance)

        // Buttons and display
        calculateTotalButton = findViewById(R.id.calculateTotalButton)
        totalFeesText = findViewById(R.id.totalFeesText)
        contactButton = findViewById(R.id.contactButton)
        backButton = findViewById(R.id.backButton)
        requestConsultationButton = findViewById(R.id.calculateFeesButton)
    }

    private fun setupClickListeners() {
        // Calculate total fees button - NOW REQUIRES CLICK
        calculateTotalButton.setOnClickListener {
            calculateAndDisplayTotal()
        }

        // Navigation buttons
        contactButton.setOnClickListener {
            val intent = Intent(this, contactDetails::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener {
            finish()
        }

        // Request consultation button
        requestConsultationButton.setOnClickListener {
            if (validateForm()) {
                requestConsultation()
            }
        }

        // REMOVED: Auto-calculate when checkboxes change
        // Now calculation only happens when button is clicked
    }

    private fun calculateAndDisplayTotal() {
        var total = 0
        var courseCount = 0

        // Six-month courses (R1500 each)
        if (checkboxFirstAid.isChecked) { total += 1500; courseCount++ }
        if (checkboxSewing.isChecked) { total += 1500; courseCount++ }
        if (checkboxLandscaping.isChecked) { total += 1500; courseCount++ }
        if (checkboxLifeSkills.isChecked) { total += 1500; courseCount++ }

        // Six-week courses (R750 each)
        if (checkboxChildMinding.isChecked) { total += 750; courseCount++ }
        if (checkboxCooking.isChecked) { total += 750; courseCount++ }
        if (checkboxGardenMaintenance.isChecked) { total += 750; courseCount++ }

        // Apply discounts based on number of courses
        val discountRate = when (courseCount) {
            1 -> 0
            2 -> 5
            3 -> 10
            else -> if (courseCount > 3) 15 else 0
        }

//     CODE ATTRIBUTION :
//     Title: Kotlin Conditions and If..Else
//     Author: W3Schools, 2025
//     Date: 20 October 2025
//     Available at: https://www.w3schools.com/kotlin/kotlin_conditions.php

        val discountAmount = (total * discountRate / 100.0).toInt()
        val totalAfterDiscount = total - discountAmount
        val vat = (totalAfterDiscount * 0.15).toInt()
        val finalTotal = totalAfterDiscount + vat

        // Update display with detailed breakdown
        if (courseCount == 0) {
            totalFeesText.text = "R0"
            Toast.makeText(this, "Please select at least one course", Toast.LENGTH_SHORT).show()
        } else {
            val displayText = if (discountRate > 0) {
                "R$finalTotal\n(Includes $discountRate% discount + VAT)"
            } else {
                "R$finalTotal\n(Includes VAT)"
            }
            totalFeesText.text = displayText
        }
    }

    private fun validateForm(): Boolean {
        val name = fullNameInput.text.toString().trim()
        val phone = phoneNumberInput.text.toString().trim()
        val email = emailAddressInput.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your full name", Toast.LENGTH_SHORT).show()
            return false
        }

        if (phone.isEmpty()) {
            Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!hasSelectedCourses()) {
            Toast.makeText(this, "Please select at least one course", Toast.LENGTH_SHORT).show()
            return false
        }

        // Check if user has calculated the fees
        if (totalFeesText.text == "R0") {
            Toast.makeText(this, "Please calculate fees first", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

//     CODE ATTRIBUTION :
//     Title: Kotlin Functions
//     Author: W3Schools, 2025
//     Date: 6 October 2025
//     Available at: https:www.w3schools.com/kotlin/kotlin_functions.php and


    private fun hasSelectedCourses(): Boolean {
        return checkboxFirstAid.isChecked || checkboxSewing.isChecked ||
                checkboxLandscaping.isChecked || checkboxLifeSkills.isChecked ||
                checkboxChildMinding.isChecked || checkboxCooking.isChecked ||
                checkboxGardenMaintenance.isChecked
    }

    private fun requestConsultation() {
        val selectedCourses = getSelectedCourseNames()
        val name = fullNameInput.text.toString().trim()
        val phone = phoneNumberInput.text.toString().trim()
        val email = emailAddressInput.text.toString().trim()

        // Create confirmation message
        val message = "Consultation Request Submitted!\n\n" +
                "Name: $name\n" +
                "Phone: $phone\n" +
                "Email: $email\n" +
                "Selected Courses: ${selectedCourses.joinToString(", ")}\n\n" +
                "Quoted Total: ${totalFeesText.text}\n\n" +
                "We will contact you soon to arrange your consultation."

        // Show confirmation dialog
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Request Submitted")
            .setMessage(message)
            .setPositiveButton("OK") { _, _ ->
                // Optionally return to main screen or clear form
                clearForm()
            }
            .show()
    }

    private fun getSelectedCourseNames(): List<String> {
        val selected = mutableListOf<String>()
        if (checkboxFirstAid.isChecked) selected.add("First Aid")
        if (checkboxSewing.isChecked) selected.add("Sewing")
        if (checkboxLandscaping.isChecked) selected.add("Landscaping")
        if (checkboxLifeSkills.isChecked) selected.add("Life Skills")
        if (checkboxChildMinding.isChecked) selected.add("Child Minding")
        if (checkboxCooking.isChecked) selected.add("Cooking")
        if (checkboxGardenMaintenance.isChecked) selected.add("Garden Maintenance")
        return selected
    }

    private fun clearForm() {
        fullNameInput.text.clear()
        phoneNumberInput.text.clear()
        emailAddressInput.text.clear()

        checkboxFirstAid.isChecked = false
        checkboxSewing.isChecked = false
        checkboxLandscaping.isChecked = false
        checkboxLifeSkills.isChecked = false
        checkboxChildMinding.isChecked = false
        checkboxCooking.isChecked = false
        checkboxGardenMaintenance.isChecked = false

        totalFeesText.text = "R0"
    }

    override fun onResume() {
        super.onResume()
        // Any additional setup when returning to this screen
    }
}