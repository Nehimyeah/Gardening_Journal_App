package com.example.gardeningJournalApp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gardeningJournalApp.R
import com.example.gardeningJournalApp.databinding.FragmentGardenLogBinding
import com.example.gardeningJournalApp.db.PlantDatabase
import kotlinx.coroutines.launch

class GardenLogFragment : BaseFragment() {
    private lateinit var binding: FragmentGardenLogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_garden_log, container, false)
        binding = FragmentGardenLogBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewPlants.setHasFixedSize(true)
        binding.recyclerViewPlants.layoutManager = LinearLayoutManager(context)
        launch {
            context?.let{
                val plants = PlantDatabase(it).getPlantDao().getAllPlants()
                binding.recyclerViewPlants.adapter = PlantsAdapter(plants)
            }
        }
        binding.buttonAdd.setOnClickListener {
            findNavController().navigate(R.id.actionAddPlant)
        }
    }
}