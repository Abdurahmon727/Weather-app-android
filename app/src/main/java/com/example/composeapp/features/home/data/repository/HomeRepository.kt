package com.example.composeapp.features.home.data.repository

import com.example.composeapp.core.domain.Either
import com.example.composeapp.core.domain.Message
import com.example.composeapp.core.retrofit.RetrofitInstance
import com.example.composeapp.features.home.data.api.HomeApi
import com.example.composeapp.features.home.data.models.cities.CitiesResponse
import com.example.composeapp.features.home.data.models.forecast.ForecastResponse

interface HomeRepository {
    suspend fun getCities(query: String): Either<CitiesResponse>
    suspend fun getForeCast(cityId: String): Either<ForecastResponse>
}

class HomeRepositoryImply(private val api: HomeApi = RetrofitInstance.homeApi) : HomeRepository {
    override suspend fun getCities(query: String): Either<CitiesResponse> = try {
        val response = api.getCities(query = query)
        Either.Right(response.body()!!)
    } catch (e: Exception) {
        Either.Left(Message(e.message ?: "unexpected error"))
    }

    override suspend fun getForeCast(cityId: String): Either<ForecastResponse> = try {
        val response = api.getForecastForWeek(cityId = "id:$cityId")
        Either.Right(response.body()!!)
    } catch (e: Exception) {
        Either.Left(Message(e.message ?: "unexpected error"))
    }
}