package com.timplifier.countries.domain.useCases

import com.timplifier.countries.domain.repositories.CountriesRepository
import javax.inject.Inject

class FetchCountriesUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository
) {
    operator fun invoke() = countriesRepository.fetchCountries()
}