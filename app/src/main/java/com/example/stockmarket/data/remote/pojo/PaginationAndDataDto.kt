package com.example.stockmarket.data.remote.pojo

import com.google.gson.annotations.SerializedName

data class PaginationAndDataDto(
    @SerializedName("code") val paginationDto: PaginationDto?,
    @SerializedName("data") val currencies: List<CurrencyDto>
)