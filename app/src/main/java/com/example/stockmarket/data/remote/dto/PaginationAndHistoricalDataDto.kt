package com.example.stockmarket.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PaginationAndHistoricalDataDto(
    @SerializedName("pagination") val paginationDto: PaginationDto,
    @SerializedName("data") val historicalData: List<HistoricalDataDto>
)
