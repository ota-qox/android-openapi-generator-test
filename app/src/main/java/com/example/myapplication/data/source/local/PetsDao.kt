package com.example.myapplication.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.Pet

@Dao
interface PetsDao {

    @Query("SELECT * FROM Pets")
    suspend fun getPets(): List<Pet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: Pet)
}