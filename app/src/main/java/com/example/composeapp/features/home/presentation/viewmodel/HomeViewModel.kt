package com.example.composeapp.features.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.core.domain.Either
import com.example.composeapp.core.domain.UiStatus
import com.example.composeapp.features.home.data.repository.HomeRepository
import com.example.composeapp.features.home.data.repository.HomeRepositoryImply
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: HomeRepository = HomeRepositoryImply()) :
    ViewModel() {
    val uiState = MutableStateFlow(HomeUiState())

    init {
        getCities(q = "Tash")
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetCities -> getCities(event.query)
        }
    }

    private fun getCities(q: String) {
        viewModelScope.launch {
            Log.i("viewModelScope", "inside")
            val response = repository.getCities(query = q)
            Log.i("viewModelScope", "isRight ${response is Either.Right}")
            Log.i("viewModelScope", "get result: $response")

        }
    }
}


data class HomeUiState(
    val status: UiStatus = UiStatus.Pure
)

sealed class HomeEvent {
    data class GetCities(val query: String) : HomeEvent()
}

