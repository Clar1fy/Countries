package com.timplifier.main.presentation.ui.fragments.main.countries

import com.timplifier.countries.core.base.BaseViewModel
import com.timplifier.countries.domain.useCases.FetchCountriesByNameUseCase
import com.timplifier.countries.domain.useCases.FetchCountriesUseCase
import com.timplifier.main.presentation.models.CountryUI
import com.timplifier.main.presentation.models.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val fetchCountriesUseCase: FetchCountriesUseCase,
    private val fetchCountriesByNameUseCase: FetchCountriesByNameUseCase
) : BaseViewModel() {
    private val _countries = mutableUiStateFlow<List<CountryUI>>()
    val countries = _countries.asStateFlow()
    private val _searchQuery = MutableStateFlow<String?>(null)
    val searchQuery = _searchQuery.asStateFlow()

    fun fetchCountries() {
        fetchCountriesUseCase().gatherRequest(_countries) { it.map { countryModel -> countryModel.toUI() } }
    }

    fun fetchCountriesByName(name: String) {
        fetchCountriesByNameUseCase(name).gatherRequest(_countries) { it.map { countryModel -> countryModel.toUI() } }
    }

    fun modifySearchQuery(query: String) {
        _searchQuery.value = query
    }

    init {
        fetchCountries()
    }
}