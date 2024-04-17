package com.example.composeapp.features.home.data.api

import AppConstants
import com.example.composeapp.features.home.data.models.cities.CitiesResponse
import com.example.composeapp.features.home.data.models.forecast.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface HomeApi {
    @GET("search.json")
    suspend fun getCities(
        @Query("key") key: String = AppConstants.APIKEY,
        @Query("q") query: String,
    ): Response<CitiesResponse>

    @GET("forecast.json")
    suspend fun getForecastForWeek(
        @Query("key") key: String = AppConstants.APIKEY,
        @Query("q") cityId: String,
    ): Response<ForecastResponse>
}