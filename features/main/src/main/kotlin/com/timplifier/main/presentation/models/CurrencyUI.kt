package com.timplifier.main.presentation.models

import com.timplifier.countries.domain.models.CurrencyModel

data class CurrencyUI(
    val name: String,
    val symbol: String
)

fun CurrencyModel.toUI() = CurrencyUI(name, symbol)