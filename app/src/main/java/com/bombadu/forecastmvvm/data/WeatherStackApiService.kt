package com.bombadu.forecastmvvm.data

import com.bombadu.forecastmvvm.data.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "bd4c3d63d5bd5b1ff6e4ee31a5283a0f"

//http://api.weatherstack.com/current?access_key=bd4c3d63d5bd5b1ff6e4ee31a5283a0f&query=New_York&Lang=en

interface WeatherStackApiService {

    @GET
    fun getCurrentWeather (
        @Query("query")location: String,
        @Query("lang") languageCode: String = "en"


    ): Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke(): WeatherStackApiService {
            val requestInterceptor = Interceptor { chain->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return  Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherStackApiService::class.java)
        }
    }
}