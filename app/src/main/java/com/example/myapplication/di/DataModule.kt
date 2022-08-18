package com.example.myapplication.di

import com.example.api.petstore.api.PetApi
import com.example.myapplication.data.source.DefaultMainRepository
import com.example.myapplication.data.source.MainDataSource
import com.example.myapplication.data.source.MainRepository
import com.example.myapplication.data.source.remote.MainRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteMainDataSource

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        mainRemoteDataSource: MainRemoteDataSource,
    ): MainRepository = DefaultMainRepository(mainRemoteDataSource)
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @RemoteMainDataSource
    @Provides
    fun provideMainRemoteDataSource(
        petApi: PetApi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): MainDataSource = MainRemoteDataSource(petApi, ioDispatcher)
}