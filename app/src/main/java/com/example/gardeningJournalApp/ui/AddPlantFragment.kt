package com.example.gardeningJournalApp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.gardeningJournalApp.R
import com.example.gardeningJournalApp.databinding.FragmentAddPlantBinding
import com.example.gardeningJournalApp.db.Plant
import com.example.gardeningJournalApp.db.PlantDatabase
import kotlinx.coroutines.launch


class AddPlantFragment : BaseFragment() {
    private lateinit var binding:FragmentAddPlantBinding
    private var plant: Plant? = null
    private val navArgs :AddPlantFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_add_plant, container, false)
        binding = FragmentAddPlantBinding.bind(view)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plant = navArgs.plant
        binding.name.setText(plant?.name)
        binding.type.setText(plant?.type)
        plant?.wateringFrequency?.let { binding.wateringFrequency.setText(it) }
        binding.plantingDate.setText(plant?.plantingDate)
//        binding.buttonDelete.setOnClickListener {
//            if (plant != null) deletePlant() else context?.toast("Cannot Delete")
//        }
        binding.addBtn.setOnClickListener { view ->
            val name = binding.name.text.toString()
            val type = binding.type.text.toString()
            val wateringFrequency = binding.wateringFrequency.text.toString()
            val plantingDate = binding.plantingDate.text.toString()
            if(name.isEmpty()){
                binding.name.error = "Name Required"
                binding.name.requestFocus()
                return@setOnClickListener
            }

            if(type.isEmpty()){
                binding.type.error = "Type Required"
                binding.type.requestFocus()
                return@setOnClickListener
            }

            if(wateringFrequency.isEmpty()){
                binding.wateringFrequency.error = "Watering Frequency Required"
                binding.wateringFrequency.requestFocus()
                return@setOnClickListener
            }

            if(plantingDate.isEmpty()){
                binding.plantingDate.error = "Planting Date Required"
                binding.plantingDate.requestFocus()
                return@setOnClickListener
            }

            launch {
                context?.let {
                    val mPlant = Plant(name, type, wateringFrequency.toInt(), plantingDate)
                    if (plant == null) {
                        PlantDatabase(it).getPlantDao().addPlant(mPlant)
                        it.toast("Plant Saved")
                    } else {
                        mPlant.id = plant!!.id
                        PlantDatabase(it).getPlantDao().updatePlant(mPlant)
                        it.toast("Plant Updated")
                    }
                    findNavController().navigate(R.id.actionSavePlant)
                }
            }
        }
   }
}