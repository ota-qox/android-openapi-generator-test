package com.example.myapplication.data.source.remote

import com.example.api.petstore.api.PetApi
import com.example.api.petstore.model.Pet
import com.example.api.petstore.model.Status
import com.example.myapplication.data.Result
import com.example.myapplication.data.Result.Success
import com.example.myapplication.data.Result.Error
import com.example.myapplication.data.source.MainDataSource
import com.example.myapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class MainRemoteDataSource @Inject constructor(
    private val petApi: PetApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): MainDataSource {
    override suspend fun findByStatus(status: Status): Result<List<Pet>> = withContext(ioDispatcher) {
        try {
            val pets = petApi.findPetsByStatus(status)
            if (pets.isSuccessful) {
                pets.body()?.let {
                    return@withContext Success(it)
                }
            }
            return@withContext Error(Exception(""))
        } catch (e: Exception) {
            return@withContext Error(e)
        }
    }

    override suspend fun savePet(pet: Pet) {
        TODO("Not yet implemented")
    }
}