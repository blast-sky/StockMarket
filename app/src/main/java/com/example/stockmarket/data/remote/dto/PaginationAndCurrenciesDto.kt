package com.example.stockmarket.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PaginationAndCurrenciesDto(
    @SerializedName("code") val paginationDto: PaginationDto?,
    @SerializedName("data") val currencies: List<CurrencyDto>
)