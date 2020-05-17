package com.bombadu.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.bombadu.forecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean) :LiveData<out UnitSpecificCurrentWeatherEntry>
}