package com.timplifier.countries.data.remote.dtos


import com.google.gson.annotations.SerializedName
import com.timplifier.countries.domain.models.CountryNameModel
import com.timplifier.karsyhkyrremastered.data.utils.DataMapper

data class CountryNameDto(
    @SerializedName("common")
    val common: String,
    @SerializedName("official")
    val official: String,
) : DataMapper<CountryNameModel> {
    override fun toDomain() = CountryNameModel(common, official)
}