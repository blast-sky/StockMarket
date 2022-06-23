package com.example.stockmarket.data.remote.pojo

import com.google.gson.annotations.SerializedName

data class PaginationAndData(
    @SerializedName("code") val pagination: Pagination,
    @SerializedName("data") val currencies: List<Currency>
)