package com.example.myapplication.data.source.local

import com.example.api.petstore.model.Pet
import com.example.api.petstore.model.Status
import com.example.myapplication.data.Result
import com.example.myapplication.data.Result.Success
import com.example.myapplication.data.Result.Error
import com.example.myapplication.data.source.MainDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainLocalDataSource internal constructor(
    private val petsDao: PetsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
): MainDataSource {

    override suspend fun findByStatus(status: Status): Result<List<Pet>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(petsDao.getPets().map { Pet(id = it.id, name = it.name, tag = it.tag) })
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun savePet(pet: Pet) {
        petsDao.insertPet(com.example.myapplication.data.Pet(id = pet.id, name = pet.name, tag = pet.tag))
    }
}