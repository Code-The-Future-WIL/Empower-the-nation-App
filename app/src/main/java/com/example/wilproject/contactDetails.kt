package com.example.wilproject

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class contactDetails : AppCompatActivity() {

    private lateinit var addressDropdown: Spinner
    private lateinit var selectedAddressText: TextView
    private lateinit var addressDisplayText: TextView
    private lateinit var mapTitleText: TextView
    private lateinit var mapImageView: ImageView
    private lateinit var backButton: ImageButton

    // Map of addresses to image resources
    private val addressMap = mapOf(
        "Address 1: Sandton" to Pair(
            "463 Main Street, Sandton, Johannesburg",
            R.drawable.map1 // Make sure you have map1.jpg in your drawable folder
        ),
        "Address 2: Melville" to Pair(
            "33 Rose Avenue, Melville, Johannesburg",
            R.drawable.map2 // Make sure you have map2.jpg in your drawable folder
        ),
        "Address 3: Soweto" to Pair(
            "24 Market Road, Soweto, Johannesburg",
            R.drawable.map3 // Make sure you have map3.jpg in your drawable folder
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactdetails)

        initializeViews()
        setupDropdown()
        setupClickListeners()
    }

    private fun initializeViews() {
        addressDropdown = findViewById(R.id.addressDropdown)
        selectedAddressText = findViewById(R.id.selectedAddressText)
        addressDisplayText = findViewById(R.id.addressDisplayText)
        mapTitleText = findViewById(R.id.mapTitleText)
        mapImageView = findViewById(R.id.mapImageView)
        backButton = findViewById(R.id.backButton)
    }

    private fun setupDropdown() {
        // Create dropdown options from address map keys
        val addressOptions = addressMap.keys.toTypedArray()

        // Create adapter for dropdown
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, addressOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        addressDropdown.adapter = adapter

        // Set dropdown selection listener
        addressDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedAddress = parent?.getItemAtPosition(position).toString()
                displayAddressAndMap(selectedAddress)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Hide address and map when nothing is selected
                hideAddressAndMap()
            }
        }
    }

    private fun displayAddressAndMap(selectedAddress: String) {
        val (fullAddress, mapResource) = addressMap[selectedAddress] ?: return

        // Show the selected address
        addressDisplayText.text = fullAddress

        // Show the map image
        mapImageView.setImageResource(mapResource)

        // Make all elements visible
        selectedAddressText.visibility = View.VISIBLE
        addressDisplayText.visibility = View.VISIBLE
        mapTitleText.visibility = View.VISIBLE
        mapImageView.visibility = View.VISIBLE
    }

    private fun hideAddressAndMap() {
        // Hide all address and map elements
        selectedAddressText.visibility = View.GONE
        addressDisplayText.visibility = View.GONE
        mapTitleText.visibility = View.GONE
        mapImageView.visibility = View.GONE
    }

    private fun setupClickListeners() {
        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        // Reset dropdown to no selection when returning to this screen
        addressDropdown.setSelection(0)
    }
}