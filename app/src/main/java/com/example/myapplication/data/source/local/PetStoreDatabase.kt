package com.example.myapplication.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.Pet

@Database(entities = [Pet::class], version = 1, exportSchema = false)
abstract class PetStoreDatabase: RoomDatabase() {

    abstract fun petDao(): PetsDao
}