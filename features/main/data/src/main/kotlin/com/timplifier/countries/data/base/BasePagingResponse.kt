package com.timplifier.countries.data.base


import com.google.gson.annotations.SerializedName

data class BasePagingResponse<T>(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?,
    @SerializedName("results")
    val results: List<T>
)