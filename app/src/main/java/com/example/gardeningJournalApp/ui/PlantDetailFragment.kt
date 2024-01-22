package com.example.gardeningJournalApp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gardeningJournalApp.R
import com.example.gardeningJournalApp.databinding.FragmentPlantDetailBinding
import com.example.gardeningJournalApp.db.Plant
import com.example.gardeningJournalApp.db.PlantDatabase
import kotlinx.coroutines.launch


class PlantDetailFragment : BaseFragment() {
    private lateinit var binding: FragmentPlantDetailBinding
    private var plant: Plant? = null
    private val navArgs : PlantDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_plant_detail, container, false)
        binding = FragmentPlantDetailBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plant = navArgs.plant
        binding.name.text = plant?.name
        binding.type.text = plant?.type
        binding.wateringFrequency.text = plant?.wateringFrequency.toString()
        binding.plantingDate.text = plant?.plantingDate
        binding.deleteBtn.setOnClickListener {
            deletePlant()
        }
        binding.editBtn.setOnClickListener {
            val action = PlantDetailFragmentDirections.detailToUpdate(plant!!)
            findNavController().navigate(action)
        }
    }

    private fun deletePlant() {
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You cannot undo this operation")
            setPositiveButton("Yes") { _, _ ->
                launch {
                    PlantDatabase(context).getPlantDao().deletePlant(plant!!)
                    findNavController().navigate(R.id.detailToLog)
                }
            }
            setNegativeButton("No") { _, _ ->
            }
        }.create().show()
    }
}