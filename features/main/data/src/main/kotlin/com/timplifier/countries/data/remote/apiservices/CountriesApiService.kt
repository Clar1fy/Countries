package com.timplifier.countries.data.remote.apiservices

import com.timplifier.countries.data.remote.dtos.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApiService {
    @GET("v2/all/")
    suspend fun fetchAllCountries(): List<CountryDto>

    @GET("v2/name/{name}/")
    suspend fun fetchCountriesByName(@Path("name") countryName: String): List<CountryDto>
}