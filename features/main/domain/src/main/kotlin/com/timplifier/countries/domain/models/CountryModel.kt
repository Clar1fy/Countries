package com.timplifier.countries.domain.models

data class CountryModel(
    val name: String,
    val symbol: String,
    val capital: String?,
    val region: String,
    val timezones: List<String>,
    val flags: CountryFlagsModel,
    val currencies: List<CurrencyModel>?,
)