package com.example.herbalappviewer


import android.os.Bundle
import android.widget.TextView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class PlantDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail)

        val plant = intent.getParcelableExtra<HerbalPlant>("plant")
        // Set the title of the activity to the plant name
        setTitle(plant?.name)

        val tvDescription: TextView = findViewById(R.id.tv_description)
        tvDescription.text = plant?.description

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable back button

        val backButton = findViewById<ImageButton>(R.id.btn_back)
        backButton.setOnClickListener {
            onBackPressed() // Navigate back when the back button is clicked
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // Navigate back when the back button in the action bar is clicked
        return true
    }
}