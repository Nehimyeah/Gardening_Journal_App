package com.example.gardeningJournalApp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningJournalApp.R
import com.example.gardeningJournalApp.db.Plant

class PlantsAdapter(private val plants: List<Plant>) : RecyclerView.Adapter<PlantsAdapter.PlantViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
       return  PlantViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.plant_layout, parent, false)
        )
    }

    override fun getItemCount() = plants.size

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = plants[position]
        holder.nameTxt.text =  plant.name
        holder.typeTxt.text = plant.type
        holder.view.setOnClickListener {
            val direction = GardenLogFragmentDirections.logToDetails(plant)
            Navigation.findNavController(it).navigate(direction)
        }

    }
    class PlantViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        var nameTxt : TextView = view.findViewById(R.id.text_view_title)
        var typeTxt : TextView = view.findViewById(R.id.text_view_plant)
    }
}