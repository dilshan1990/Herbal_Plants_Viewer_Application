package com.example.herbalappviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var plants: MutableList<HerbalPlant>
    private lateinit var adapter: HerbalPlantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plants = createHerbalPlants()
        adapter = HerbalPlantAdapter(this, plants) { plant ->
            showPlantDetail(plant)
        }

        val recyclerView: RecyclerView = findViewById(R.id.rvplants)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Image button to add a new herbal plant
        val btnAdd: ImageButton = findViewById(R.id.btn_add)
        btnAdd.setOnClickListener {
            showAddPlantDialog()
        }
    }

    private fun createHerbalPlants(): MutableList<HerbalPlant> {
        val plants = mutableListOf<HerbalPlant>()
        // Populate with initial data, you can replace this with actual data source
        plants.add(HerbalPlant("Gotu Kola (Centella asiatica)", "Gotu kola is a small, perennial herbaceous plant that grows in damp, marshy areas. It's renowned for its medicinal properties, particularly for improving memory and cognition.Neem is a versatile tree native to the Indian subcontinent, including Sri Lanka. Its leaves, seeds, and bark are all used for medicinal purposes. In Sri Lankan herbal medicine, neem is valued for its antibacterial, antiviral, and antifungal properties. It's commonly used to treat skin disorders, digestive issues, and as an insect repellent. In Sri Lankan traditional medicine, it's often used to treat various ailments including anxiety, skin conditions, and wounds.", R.drawable.plant_1))
        plants.add(HerbalPlant("Neem (Azadirachta indica):", "Neem is a versatile tree native to the Indian subcontinent, including Sri Lanka. Its leaves, seeds, and bark are all used for medicinal purposes. In Sri Lankan herbal medicine, neem is valued for its antibacterial, antiviral, and antifungal properties. Sri Lankan cardamom, also known as \"true cardamom\" or \"green cardamom,\" is a perennial herbaceous plant native to the country. It's highly prized for its culinary uses as a spice, particularly in curries and desserts. In addition to its aromatic flavor, cardamom also possesses medicinal properties and is used in traditional medicine to aid digestion, freshen breath, and alleviate respiratory ailments.It's commonly used to treat skin disorders, digestive issues, and as an insect repellent.",R.drawable.plant_2))
        plants.add(HerbalPlant("Sri Lankan Cardamom (Elettaria cardamomum):", "Ginger is a flowering plant native to Southeast Asia, including Sri Lanka. It's widely cultivated for its rhizome, which is commonly used as a spice and for its medicinal properties. In Sri Lankan herbal medicine, ginger is valued for its ability to alleviate nausea, aid digestion, and reduce inflammation. It's often consumed as a tea or added to various dishes for flavor and health benefits.Sri Lankan cardamom, also known as \"true cardamom\" or \"green cardamom,\" is a perennial herbaceous plant native to the country. It's highly prized for its culinary uses as a spice, particularly in curries and desserts. In addition to its aromatic flavor, cardamom also possesses medicinal properties and is used in traditional medicine to aid digestion, freshen breath, and alleviate respiratory ailments.",R.drawable.plant_3))
        plants.add(HerbalPlant("Ginger (Zingiber officinale):", "Ginger is a flowering plant native to Southeast Asia, including Sri Lanka. It's widely cultivated for its rhizome, which is commonly used as a spice and for its medicinal properties. In Sri Lankan herbal medicine, ginger is valued for its ability to alleviate nausea, aid digestion, and reduce inflammation. It's often consumed as a tea or added to various dishes for flavor and health benefits.",R.drawable.plant_4))
        plants.add(HerbalPlant("Turmeric (Curcuma longa):", "Turmeric is a member of the ginger family and is native to South Asia, including Sri Lanka. It's well-known for its bright yellow color and culinary uses as a spice, particularly in curries. In addition to its culinary applications, turmeric is highly valued in traditional medicine for its anti-inflammatory and antioxidant properties. It's used to treat various ailments including arthritis, digestive issues, and skin conditions.Turmeric is a member of the ginger family and is native to South Asia, including Sri Lanka. It's well-known for its bright yellow color and culinary uses as a spice, particularly in curries. In addition to its culinary applications, turmeric is highly valued in traditional medicine for its anti-inflammatory and antioxidant properties. It's used to treat various ailments including arthritis, digestive issues, and skin conditions.",R.drawable.plant_5))
        // Add more plants as needed
        return plants
    }

    private fun showAddPlantDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_plant, null)
        dialogBuilder.setView(dialogView)

        val etName = dialogView.findViewById<EditText>(R.id.et_plant_name)
        val etDescription = dialogView.findViewById<EditText>(R.id.et_plant_description)

        dialogBuilder.setTitle("Add New Plant")
        dialogBuilder.setMessage("Enter the details for the new plant:")
        dialogBuilder.setPositiveButton("Add") { dialog, _ ->
            val name = etName.text.toString()
            val description = etDescription.text.toString()
            if (name.isNotEmpty() && description.isNotEmpty()) {
                // Assuming you have a function to get the background image resource ID based on the plant name
                val backgroundImageResId = getBackgroundImageResId(name)
                val newPlant = HerbalPlant(name, description, backgroundImageResId)
                plants.add(newPlant)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "New herbal plant added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter both name and description", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun getBackgroundImageResId(plantName: String): Int {
        // Assuming you have a map or another data structure to store the background image resource IDs for each plant name
        val backgroundImageResIds = mapOf(
            "Gotu Kola (Centella asiatica)" to R.drawable.plant_1,
            "Basil" to R.drawable.plant_2
            // Add more mappings as needed
        )

        // Return the background image resource ID corresponding to the plant name
        return backgroundImageResIds[plantName] ?: R.drawable.plant_11 // Default background image if not found
    }

    private fun showPlantDetail(plant: HerbalPlant) {
        val intent = Intent(this, PlantDetailActivity::class.java)
        intent.putExtra("plant", plant)
        startActivity(intent)
    }
}