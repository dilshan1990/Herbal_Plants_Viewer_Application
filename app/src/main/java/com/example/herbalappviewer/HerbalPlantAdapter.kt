package com.example.herbalappviewer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.herbalappviewer.R

class HerbalPlantAdapter(
    private val context: Context,
    private val plants: MutableList<HerbalPlant>,
    private val onItemClick: (HerbalPlant) -> Unit
) : RecyclerView.Adapter<HerbalPlantAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPlantName: TextView = itemView.findViewById(R.id.tvplantname)
        val cardView: CardView = itemView.findViewById(R.id.rvcard)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(plants[position])
                }
            }

            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    showDeleteConfirmationDialog(plants[position])
                    true // Indicate that the long click event is consumed
                } else {
                    false
                }
            }
        }

        private fun showDeleteConfirmationDialog(plant: HerbalPlant) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Confirm Deletion")
            builder.setMessage("Are you sure you want to delete ${plant.name}?")
            builder.setPositiveButton("Delete") { _, _ ->
                plants.remove(plant)
                notifyDataSetChanged()
                Toast.makeText(context, "${plant.name} deleted", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancel", null)
            builder.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = plants[position]
        holder.tvPlantName.text = plant.name
        holder.cardView.setBackgroundResource(plant.backgroundImageResId)
    }

    override fun getItemCount() = plants.size
}
