package com.timplifier.countries.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.timplifier.countries.domain.models.CurrencyModel
import com.timplifier.karsyhkyrremastered.data.utils.DataMapper

data class CurrencyDto(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String
) : DataMapper<CurrencyModel> {
    override fun toDomain() = CurrencyModel(code, name, symbol)
}