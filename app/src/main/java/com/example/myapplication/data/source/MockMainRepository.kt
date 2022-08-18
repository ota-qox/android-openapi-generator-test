package com.example.myapplication.data.source

import com.example.api.petstore.model.Pet
import com.example.api.petstore.model.Status
import com.example.myapplication.data.Result

class MockMainRepository: MainRepository {

    override suspend fun findByStatus(status: Status): Result<List<Pet>> {
        return Result.Success(listOf(
            Pet(id = 1, name = "DogA", tag = null),
            Pet(id = 2, name = "DogB", tag = null),
            Pet(id = 3, name = "DogC", tag = null),
            Pet(id = 4, name = "DogD", tag = null)
        ))
    }
}