package com.example.gardeningJournalApp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Plant::class],
    version = 1
)
abstract class PlantDatabase: RoomDatabase() {
    abstract fun getPlantDao(): PlantDao

    companion object {
        @Volatile
        private var instance: PlantDatabase? = null
        private val LOCK =   Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PlantDatabase::class.java,
            "plantDatabase"
        ).build()
    }
}