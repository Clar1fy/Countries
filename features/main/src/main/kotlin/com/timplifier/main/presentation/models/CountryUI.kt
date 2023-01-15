package com.timplifier.main.presentation.models

import com.timplifier.countries.core.base.BaseDiffModel
import com.timplifier.countries.domain.models.CountryModel

data class CountryUI(
    val name: String,
    override val symbol: String,
    val capital: String?,
    val region: String,
    val timezones: List<String>,
    val flags: CountryFlagsUI,
    val currencies: List<CurrencyUI>?,
) : BaseDiffModel<String>

fun CountryModel.toUI() =
    CountryUI(
        name, symbol, capital, region, timezones, flags.toUI(), currencies?.map { it.toUI() }
    )