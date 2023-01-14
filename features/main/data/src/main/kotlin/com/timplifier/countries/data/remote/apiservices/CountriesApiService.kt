package com.timplifier.countries.data.remote.apiservices

import com.timplifier.countries.data.remote.dtos.CountryDto
import retrofit2.http.GET

interface CountriesApiService {
    @GET("v3.1/all/")
    suspend fun getAllCountries(): List<CountryDto>
}