package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.api.petstore.api.PetApi
import com.example.myapplication.data.source.DefaultMainRepository
import com.example.myapplication.data.source.MainDataSource
import com.example.myapplication.data.source.MainRepository
import com.example.myapplication.data.source.local.MainLocalDataSource
import com.example.myapplication.data.source.local.PetStoreDatabase
import com.example.myapplication.data.source.remote.MainRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteMainDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalMainDataSource

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        @RemoteMainDataSource mainRemoteDataSource: MainDataSource,
        @LocalMainDataSource localMainDataSource: MainDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): MainRepository = DefaultMainRepository(mainRemoteDataSource, localMainDataSource, ioDispatcher)
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

    @Singleton
    @LocalMainDataSource
    @Provides
    fun provideMainLocalDataSource(
        database: PetStoreDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): MainDataSource = MainLocalDataSource(database.petDao(), ioDispatcher)
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PetStoreDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            PetStoreDatabase::class.java,
            "Pets.db"
        ).build()
    }
}