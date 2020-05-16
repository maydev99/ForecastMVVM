package com.bombadu.forecastmvvm.data.network

import androidx.lifecycle.LiveData
import com.bombadu.forecastmvvm.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String
    )
}