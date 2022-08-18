package com.example.myapplication

import android.app.Application
import com.example.api.petstore.api.PetApi
import com.example.api.petstore.model.Status
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}