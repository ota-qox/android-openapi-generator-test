package com.example.api.petstore.api

import com.example.api.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody

import com.example.api.petstore.model.Pet
import com.example.api.petstore.model.Status

interface PetApi {
    /**
     * Finds Pets by status
     * Multiple status values can be provided with comma separated strings
     * Responses:
     *  - 200: successful operation
     *  - 400: Invalid status value
     *
     * @param status Status values that need to be considered for filter
     * @return [kotlin.collections.List<Pet>]
     */
    @GET("pet/findByStatus")
    suspend fun findPetsByStatus(@Query("status") status: Status): Response<kotlin.collections.List<Pet>>

}
