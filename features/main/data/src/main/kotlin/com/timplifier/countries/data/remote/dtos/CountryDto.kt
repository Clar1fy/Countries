package com.timplifier.countries.data.remote.dtos


import com.google.gson.annotations.SerializedName
import com.timplifier.countries.domain.models.CountryModel
import com.timplifier.karsyhkyrremastered.data.utils.DataMapper

data class CountryDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("alpha3Code")
    val symbol: String,
    @SerializedName("capital")
    val capital: String?,
    @SerializedName("region")
    val region: String,
    @SerializedName("timezones")
    val timezones: List<String>,
    @SerializedName("flags")
    val flags: CountryFlagsDto,
    @SerializedName("currencies")
    val currencies: List<CurrencyDto>?,
) : DataMapper<CountryModel> {
    override fun toDomain() = CountryModel(
        name,
        symbol,
        capital,
        region,
        timezones,
        flags.toDomain(),
        currencies?.map { it.toDomain() }
    )
}