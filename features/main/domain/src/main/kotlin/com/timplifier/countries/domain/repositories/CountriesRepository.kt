package com.timplifier.countries.domain.repositories

import com.timplifier.countries.common.either.Either
import com.timplifier.countries.domain.models.CountryModel
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    fun fetchCountries(): Flow<Either<String, List<CountryModel>>>
}