package com.timplifier.countries.domain.models

data class CountryModel(
    val name: CountryNameModel?,
    val symbol: String,
    val currencies: CurrencyModel?,
    val capital: List<String>?,
    val region: String,
    val timezones: List<String>,
    val flags: CountryFlagsModel,
)