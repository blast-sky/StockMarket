package com.example.stockmarket.data.remote.dto.pagination

import com.example.stockmarket.data.remote.dto.CurrencyDto
import com.google.gson.annotations.SerializedName

data class PaginationAndCurrenciesDto(
    @SerializedName("code") val paginationDto: PaginationDto?,
    @SerializedName("data") val currencies: List<CurrencyDto>
)