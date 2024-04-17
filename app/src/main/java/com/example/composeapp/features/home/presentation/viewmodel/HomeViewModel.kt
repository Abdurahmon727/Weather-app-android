package com.example.composeapp.features.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.core.domain.Either
import com.example.composeapp.features.home.data.repository.HomeRepository
import com.example.composeapp.features.home.data.repository.HomeRepositoryImply
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: HomeRepository = HomeRepositoryImply()) :
    ViewModel() {
    val uiState = MutableStateFlow("")

    init {
        onCall()
    }

    private fun onCall() {
        viewModelScope.launch {
            Log.i("viewModelScope", "inside")
            val response = repository.getCities("Tash")
            Log.i("viewModelScope", "isRight ${response is Either.Right}")
            Log.i("viewModelScope", "get result: $response")
        }
    }
}