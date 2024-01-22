package com.example.gardeningJournalApp.db

import androidx.room.*

@Dao
interface PlantDao {
    @Insert
    suspend fun addPlant(plant:Plant)

    @Query("SELECT * FROM plant ORDER BY id DESC")
    suspend fun getAllPlants():List<Plant>

    @Insert
    suspend fun addMultiplePlants(vararg plant: Plant)

    @Update
    suspend fun updatePlant(plant:Plant)

    @Delete
    suspend fun deletePlant(plant: Plant)
}