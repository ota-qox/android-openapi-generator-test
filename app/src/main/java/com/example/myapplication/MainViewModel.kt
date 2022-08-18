package com.example.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.petstore.model.Pet
import com.example.api.petstore.model.Status
import com.example.myapplication.data.Result
import com.example.myapplication.data.source.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainRepository: MainRepository
): ViewModel() {
    var pets: List<Pet> by mutableStateOf(emptyList())
        private set

    init {
        viewModelScope.launch {
            when(val result = mainRepository.findByStatus(Status.sold)) {
                is Result.Success -> {
                    pets = result.data
                }
                is Result.Error -> {

                }
            }
        }
    }
}