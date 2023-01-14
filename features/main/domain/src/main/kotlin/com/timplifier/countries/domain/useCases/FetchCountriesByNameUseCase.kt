package com.timplifier.countries.domain.useCases

import com.timplifier.countries.domain.repositories.CountriesRepository
import javax.inject.Inject

class FetchCountriesByNameUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository
) {
    operator fun invoke(name: String) = countriesRepository.fetchCountriesByName(name)
}