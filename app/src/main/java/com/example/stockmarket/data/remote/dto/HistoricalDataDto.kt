package com.example.stockmarket.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HistoricalDataDto(
    @SerializedName("date") val date: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("exchange") val exchange: String,
    @SerializedName("open") val open: Double? = null,
    @SerializedName("high") val high: Double,
    @SerializedName("low") val low: Double,
    @SerializedName("close") val close: Double? = null,
    @SerializedName("volume") val volume: Int? = null,
    @SerializedName("adj_open") val adjOpen: Double? = null,
    @SerializedName("adj_high") val adjHigh: Double? = null,
    @SerializedName("adj_low") val adjLow: Double? = null,
    @SerializedName("adj_close") val adjClose: Double? = null,
    @SerializedName("adj_volume") val adjVolume: Int? = null
)
