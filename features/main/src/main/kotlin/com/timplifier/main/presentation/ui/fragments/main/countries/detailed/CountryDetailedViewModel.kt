package com.timplifier.main.presentation.ui.fragments.main.countries.detailed

import com.timplifier.countries.core.base.BaseViewModel
import com.timplifier.countries.domain.useCases.FetchCountriesByNameUseCase
import com.timplifier.main.presentation.models.CountryUI
import com.timplifier.main.presentation.models.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CountryDetailedViewModel @Inject constructor(
    private val fetchCountriesByNameUseCase: FetchCountriesByNameUseCase
) : BaseViewModel() {
    private val _country = mutableUiStateFlow<CountryUI>()
    val country = _country.asStateFlow()

    fun fetchCountryByName(name: String) {
        fetchCountriesByNameUseCase(name).gatherRequest(_country) { it.first().toUI() }
    }
}