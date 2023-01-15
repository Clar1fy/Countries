package com.timplifier.countries.data.remote

import com.timplifier.countries.data.remote.apiservices.CountriesApiService
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkClient @Inject constructor() {
    private val retrofit =
        provideRetrofit(
            provideOkHttpClientBuilder().build()
        )

    fun generateCountriesApiService() = retrofit.createAnApi<CountriesApiService>()

    private inline fun <reified T : Any> Retrofit.createAnApi(): T = create(T::class.java)
}