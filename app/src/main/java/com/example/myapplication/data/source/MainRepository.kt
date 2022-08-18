package com.example.myapplication.data.source

import com.example.myapplication.data.Result

import com.example.api.petstore.model.Pet
import com.example.api.petstore.model.Status

interface MainRepository {

    suspend fun findByStatus(status: Status): Result<List<Pet>>
}