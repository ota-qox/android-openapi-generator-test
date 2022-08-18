package com.example.myapplication.di

import com.example.api.infrastructure.ApiClient
import com.example.api.petstore.api.PetApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providePetApi(): PetApi {
        return ApiClient()
            .setLogger {
                println(it)
            }
            .createService(PetApi::class.java)
    }
}