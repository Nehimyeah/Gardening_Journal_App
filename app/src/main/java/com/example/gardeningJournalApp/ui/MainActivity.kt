package com.example.gardeningJournalApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.gardeningJournalApp.R
import com.example.gardeningJournalApp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Setting Navigation to our Application by passing current context and navigation host fragment id
        val navController = Navigation.findNavController(this,R.id.fragment)
        /*By calling this method, the title in the action bar will automatically
          be updated when the destination changes*/
        NavigationUI.setupActionBarWithNavController(this,navController)
   }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.fragment),null)
    }
}