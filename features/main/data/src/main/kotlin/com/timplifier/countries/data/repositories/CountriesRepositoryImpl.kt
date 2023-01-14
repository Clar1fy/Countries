package com.timplifier.countries.data.repositories

import com.timplifier.countries.data.base.makeNetworkRequest
import com.timplifier.countries.data.remote.apiservices.CountriesApiService
import com.timplifier.countries.domain.repositories.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val countriesApiService: CountriesApiService
) : CountriesRepository {
    override fun fetchCountries() = makeNetworkRequest {
        countriesApiService.getAllCountries().map { it.toDomain() }
    }
}