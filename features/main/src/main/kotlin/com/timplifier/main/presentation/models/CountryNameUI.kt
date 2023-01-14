package com.timplifier.main.presentation.models

import com.timplifier.countries.domain.models.CountryNameModel

data class CountryNameUI(
    val common: String,
    val official: String,
)

fun CountryNameModel.toUI() = CountryNameUI(common, official)