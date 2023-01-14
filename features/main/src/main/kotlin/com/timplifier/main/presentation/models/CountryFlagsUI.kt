package com.timplifier.main.presentation.models

import com.timplifier.countries.domain.models.CountryFlagsModel

data class CountryFlagsUI(
    val png: String,
    val svg: String
)

fun CountryFlagsModel.toUI() = CountryFlagsUI(png, svg)