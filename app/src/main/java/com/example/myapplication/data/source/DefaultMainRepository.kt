package com.example.myapplication.data.source

import com.example.myapplication.data.Result

import com.example.api.petstore.model.Pet
import com.example.api.petstore.model.Status
import com.example.myapplication.data.source.remote.MainRemoteDataSource
import java.lang.Exception
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val mainRemoteDataSource: MainRemoteDataSource,
): MainRepository {
    override suspend fun findByStatus(status: Status): Result<List<Pet>> {
        try {
            return mainRemoteDataSource.findByStatus(status)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}