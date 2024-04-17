package com.example.composeapp.features.home.presentation.viewmodel

import AppConstants
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.core.domain.Either
import com.example.composeapp.core.domain.UiStatus
import com.example.composeapp.features.home.data.models.forecast.ForecastResponse
import com.example.composeapp.features.home.data.repository.HomeRepository
import com.example.composeapp.features.home.data.repository.HomeRepositoryImply
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: HomeRepository = HomeRepositoryImply()) : ViewModel() {
    val uiState = MutableStateFlow(HomeUiState())

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetCities -> getCities(event.query)
            is HomeEvent.GetForecast -> getForecast()
        }
    }

    private fun getCities(q: String) {
        viewModelScope.launch {
            val response = repository.getCities(query = q)
        }
    }

    private fun getForecast() {
        uiState.update { it.copy(status = UiStatus.Loading) }
        viewModelScope.launch {
            val result = repository.getForeCast(AppConstants.tashkentId)
            if (result is Either.Right) {
                uiState.update {
                    it.copy(
                        status = UiStatus.Success, forecast = result.data
                    )
                }
            } else {
                uiState.update { it.copy(status = UiStatus.Failure) }
            }
        }
    }
}


data class HomeUiState(
    val status: UiStatus = UiStatus.Pure,
    val forecast: ForecastResponse? = null,

    )

sealed class HomeEvent {
    data class GetCities(val query: String) : HomeEvent()


    object GetForecast : HomeEvent()
}

