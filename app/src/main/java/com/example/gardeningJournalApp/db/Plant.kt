package com.example.gardeningJournalApp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Plant(
    val name :String,
    val type :String,
    val wateringFrequency: Int,
    val plantingDate: String
): Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
