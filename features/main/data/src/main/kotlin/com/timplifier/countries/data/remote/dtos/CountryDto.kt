package com.timplifier.countries.data.remote.dtos


import com.google.gson.annotations.SerializedName
import com.timplifier.countries.domain.models.CurrencyModel

data class CountryItem(
    @SerializedName("name")
    val name: Name,
    @SerializedName("currencies")
    val currencies: CurrencyModel?,
    @SerializedName("capital")
    val capital: List<String>?,
    @SerializedName("timezones")
    val timezones: List<String>,
    @SerializedName("flags")
    val flags: Flags,
    @SerializedName("capitalInfo")
    val capitalInfo: CapitalInfo?,
)