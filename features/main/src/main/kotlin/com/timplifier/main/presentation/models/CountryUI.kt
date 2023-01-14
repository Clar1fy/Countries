package com.timplifier.main.presentation.models

import com.timplifier.countries.core.base.BaseDiffModel
import com.timplifier.countries.domain.models.CountryModel

data class CountryUI(
    val name: CountryNameUI?,
    override val symbol: String,
    val currencies: CurrencyUI?,
    val capital: List<String>?,
    val region: String,
    val timezones: List<String>,
    val flags: CountryFlagsUI,
) : BaseDiffModel<String>

fun CountryModel.toUI() =
    CountryUI(name?.toUI(), symbol, currencies?.toUI(), capital, region, timezones, flags.toUI())