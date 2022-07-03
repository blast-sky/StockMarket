package com.example.stockmarket.data.remote.dto.pagination

import com.example.stockmarket.data.remote.dto.TickerDto
import com.google.gson.annotations.SerializedName

data class PaginationAndTickers(
    @SerializedName("pagination") val paginationDto: PaginationDto,
    @SerializedName("data") val tickers: List<TickerDto>
)
