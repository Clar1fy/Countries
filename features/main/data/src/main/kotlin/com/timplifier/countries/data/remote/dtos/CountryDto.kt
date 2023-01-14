package com.timplifier.countries.data.remote.dtos


import com.google.gson.annotations.SerializedName
import com.timplifier.countries.domain.models.CountryModel
import com.timplifier.karsyhkyrremastered.data.utils.DataMapper

data class CountryDto(
    @SerializedName("name")
    val name: CountryNameDto?,
    @SerializedName("cca2")
    val symbol: String,
    @SerializedName("currencies")
    val currencies: CurrencyDto?,
    @SerializedName("capital")
    val capital: List<String>?,
    @SerializedName("region")
    val region: String,
    @SerializedName("timezones")
    val timezones: List<String>,
    @SerializedName("flags")
    val flags: CountryFlagsDto,
) : DataMapper<CountryModel> {
    override fun toDomain() = CountryModel(
        name?.toDomain(),
        symbol,
        currencies?.toDomain(),
        capital,
        region,
        timezones,
        flags.toDomain()
    )
}