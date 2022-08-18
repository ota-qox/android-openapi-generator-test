package com.example.api.petstore.api

import com.example.api.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody

import com.example.api.petstore.model.Error
import com.example.api.petstore.model.Pet

interface PetsApi {
    /**
     * Create a pet
     * 
     * Responses:
     *  - 201: Null response
     *  - 0: unexpected error
     *
     * @return [Unit]
     */
    @POST("pets")
    suspend fun createPets(): Response<Unit>

    /**
     * List all pets
     * 
     * Responses:
     *  - 200: A paged array of pets
     *  - 0: unexpected error
     *
     * @param limit How many items to return at one time (max 100) (optional)
     * @return [kotlin.collections.List<Pet>]
     */
    @GET("pets")
    suspend fun listPets(@Query("limit") limit: kotlin.Int? = null): Response<kotlin.collections.List<Pet>>

    /**
     * Info for a specific pet
     * 
     * Responses:
     *  - 200: Expected response to a valid request
     *  - 0: unexpected error
     *
     * @param petId The id of the pet to retrieve
     * @return [Pet]
     */
    @GET("pets/{petId}")
    suspend fun showPetById(@Path("petId") petId: kotlin.String): Response<Pet>

}
