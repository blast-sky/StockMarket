package com.example.stockmarket.data.remote.dto

import com.google.gson.annotations.SerializedName

data class StockExchangeDto(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String? = null,
    @SerializedName("country_code") val countryCode: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("website") val website: String? = null
)
