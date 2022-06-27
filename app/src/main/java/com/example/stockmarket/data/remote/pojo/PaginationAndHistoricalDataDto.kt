package com.example.stockmarket.data.remote.pojo

import com.google.gson.annotations.SerializedName

data class PaginationAndHistoricalDataDto(
    @SerializedName("pagination") val paginationDto: PaginationDto,
    @SerializedName("data") val historicalDates: List<HistoricalDataDto>
)
