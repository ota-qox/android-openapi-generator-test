package com.example.myapplication.data.source

import com.example.myapplication.data.Result

import com.example.api.petstore.model.Pet
import com.example.api.petstore.model.Status
import com.example.myapplication.data.source.local.MainLocalDataSource
import com.example.myapplication.data.source.remote.MainRemoteDataSource
import com.example.myapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.Exception

class DefaultMainRepository @Inject constructor(
    private val mainRemoteDataSource: MainDataSource,
    private val mainLocalDataSource: MainDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
): MainRepository {

    override suspend fun findByStatus(status: Status): Result<List<Pet>> {
        if (true) {
            try {
                updatePetsFromRemoteDataSource(status)
            } catch (e: Exception) {
                return Result.Error(e)
            }
        }
        return mainLocalDataSource.findByStatus(status)
    }

    private suspend fun updatePetsFromRemoteDataSource(status: Status) {
        val remotePets = mainRemoteDataSource.findByStatus(status)

        if (remotePets is Result.Success) {
            remotePets.data.forEach { pet ->
                mainLocalDataSource.savePet(pet)
            }
        } else if (remotePets is Result.Error) {
            throw remotePets.exception
        }
    }
}