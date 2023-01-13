package com.timplifier.countries.data.remote.dtos


import com.google.gson.annotations.SerializedName

data class EngX(
    @SerializedName("f")
    val f: String,
    @SerializedName("m")
    val m: String
)