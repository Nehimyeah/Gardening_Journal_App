package com.example.gardeningJournalApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gardeningJournalApp.databinding.FragmentUpdatePlantBinding
import com.example.gardeningJournalApp.db.Plant
import com.example.gardeningJournalApp.db.PlantDatabase
import com.example.gardeningJournalApp.ui.AddPlantFragmentArgs
import com.example.gardeningJournalApp.ui.BaseFragment
import com.example.gardeningJournalApp.ui.toast
import kotlinx.coroutines.launch


class UpdatePlantFragment : BaseFragment() {
    private lateinit var binding: FragmentUpdatePlantBinding
    private var plant: Plant? = null
    private val navArgs : AddPlantFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_update_plant, container, false)
        binding = FragmentUpdatePlantBinding.bind(view)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plant = navArgs.plant
        binding.name.setText(plant?.name)
        binding.type.setText(plant?.type)
        plant?.wateringFrequency?.let { binding.wateringFrequency.setText(it.toString()) }
        binding.plantingDate.setText(plant?.plantingDate)
        binding.updateBtn.setOnClickListener { view ->
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
                    mPlant.id = plant!!.id
                    PlantDatabase(it).getPlantDao().updatePlant(mPlant)
                    it.toast("Plant Updated")
                    val direction = UpdatePlantFragmentDirections.updateToDetail(mPlant)
                    findNavController().navigate(direction)
                }
            }
        }
    }
}