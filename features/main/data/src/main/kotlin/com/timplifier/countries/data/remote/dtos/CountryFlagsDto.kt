package com.timplifier.countries.data.remote.dtos


import com.google.gson.annotations.SerializedName
import com.timplifier.countries.domain.models.CountryFlagsModel
import com.timplifier.karsyhkyrremastered.data.utils.DataMapper

data class CountryFlagsDto(
    @SerializedName("png")
    val png: String,
    @SerializedName("svg")
    val svg: String
) : DataMapper<CountryFlagsModel> {
    override fun toDomain() = CountryFlagsModel(png, svg)
}