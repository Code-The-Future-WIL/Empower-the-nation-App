package com.example.wilproject

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class contactDetails : AppCompatActivity() {

    private lateinit var addressDropdown: Spinner
    private lateinit var selectedAddressText: TextView
    private lateinit var addressDisplayText: TextView
    private lateinit var mapTitleText: TextView
    private lateinit var mapWebView: WebView
    private lateinit var backButton: ImageButton

    // Map of addresses to Google Maps embed URLs
    private val addressMap = mapOf(
        "Address 1: Sandton" to Pair(
            "463 Main Street, Sandton, Johannesburg",
            """<iframe src="https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d3607221.328786729!2d26.961782926062682!3d-28.003774496523263!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e0!4m5!1s0x1ef707aa871594bd%3A0x8f02f03f86d820cd!2sThe%20IIE's%20Varsity%20College%20-%20Durban%20North%2C%20Radar%20Drive%2C%20Durban%20North%2C%20Durban!3m2!1d-29.7967586!2d31.035617799999997!4m5!1s0x1e950e6fe15db76f%3A0xf0b211a2d94be8bf!2s463%20Main%20Street%2C%20Jeppestown%2C%20Johannesburg!3m2!1d-26.2024187!2d28.0782976!5e0!3m2!1sen!2sza!4v1761229305819!5m2!1sen!2sza" width="100%" height="100%" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>"""
        ),
        "Address 2: Melville" to Pair(
            "33 Rose Avenue, Melville, Johannesburg",
            """<iframe src="https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d3606566.4211793155!2d26.840088964236294!3d-28.023329016880563!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e0!4m5!1s0x1ef707aa871594bd%3A0x8f02f03f86d820cd!2sThe%20IIE's%20Varsity%20College%20-%20Durban%20North%2C%20Radar%20Drive%2C%20Durban%20North%2C%20Durban!3m2!1d-29.7967586!2d31.035617799999997!4m5!1s0x1e95a5fcd021a34f%3A0xdfa70b2bc402fe81!2s33%20Rose%20Avenue%2C%20Lenasia!3m2!1d-26.3205703!2d27.8274954!5e0!3m2!1sen!2sza!4v1761229319788!5m2!1sen!2sza" width="100%" height="100%" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>"""
        ),
        "Address 3: Soweto" to Pair(
            "24 Market Road, Soweto, Johannesburg",
            """<iframe src="https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d3607819.243319487!2d26.89745292161772!3d-27.98591073527555!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e0!4m5!1s0x1ef707aa871594bd%3A0x8f02f03f86d820cd!2sThe%20IIE's%20Varsity%20College%20-%20Durban%20North%2C%20Radar%20Drive%2C%20Durban%20North%2C%20Durban!3m2!1d-29.7967586!2d31.035617799999997!4m5!1s0x1e950a58432f96ab%3A0xf82ce26908d67f01!2s24%20Market%20Road%2C%20Newlands%2C%20Johannesburg!3m2!1d-26.1749272!2d27.9578858!5e0!3m2!1sen!2sza!4v1761229333961!5m2!1sen!2sza" width="100%" height="100%" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>"""
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactdetails)

        initializeViews()
        setupWebView()
        setupDropdown()
        setupClickListeners()
    }

    private fun initializeViews() {
        addressDropdown = findViewById(R.id.addressDropdown)
        selectedAddressText = findViewById(R.id.selectedAddressText)
        addressDisplayText = findViewById(R.id.addressDisplayText)
        mapTitleText = findViewById(R.id.mapTitleText)
        mapWebView = findViewById(R.id.mapWebView)
        backButton = findViewById(R.id.backButton)
    }

    private fun setupWebView() {
        // Enable JavaScript for Google Maps
        mapWebView.settings.javaScriptEnabled = true
        mapWebView.settings.domStorageEnabled = true
        mapWebView.settings.loadWithOverviewMode = true
        mapWebView.settings.useWideViewPort = true

        // Set WebView client to handle page loading
        mapWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Map loaded successfully
            }
        }
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
        val (fullAddress, mapHtml) = addressMap[selectedAddress] ?: return

        // Show the selected address
        addressDisplayText.text = fullAddress

        // Load the Google Maps embed in WebView
        val htmlData = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    body { margin: 0; padding: 0; }
                    iframe { border: none; }
                </style>
            </head>
            <body>
                $mapHtml
            </body>
            </html>
        """.trimIndent()

        mapWebView.loadData(htmlData, "text/html", "UTF-8")

        // Make all elements visible
        selectedAddressText.visibility = View.VISIBLE
        addressDisplayText.visibility = View.VISIBLE
        mapTitleText.visibility = View.VISIBLE
        mapWebView.visibility = View.VISIBLE
    }

    private fun hideAddressAndMap() {
        // Hide all address and map elements
        selectedAddressText.visibility = View.GONE
        addressDisplayText.visibility = View.GONE
        mapTitleText.visibility = View.GONE
        mapWebView.visibility = View.GONE
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

    override fun onBackPressed() {
        // Handle WebView back navigation if needed
        if (mapWebView.canGoBack()) {
            mapWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}