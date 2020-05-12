package com.bombadu.forecastmvvm.data.response

import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    val currentWeatherEntry: CurrentWeatherEntry?,
    val location: Location?,
    @SerializedName("current")
    val request: Request?
)