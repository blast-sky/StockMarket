package com.example.stockmarket.data.remote

import com.example.stockmarket.Config
import com.example.stockmarket.data.remote.pojo.PaginationAndDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface StockMarketService {

    @GET("v1/currencies")
    suspend fun getCurrencies(
        @Query("access_key") token: String = TOKEN,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<PaginationAndDataDto>

    @GET("v1/eod")
    suspend fun getHistoricalData(
        @Query("access_key") token: String = TOKEN,
        @Query("symbols") symbols: String,
        @Query("date_from") date_from: Date,
        @Query("date_to") date_to: Date,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    )

    companion object {
        const val BASE_URL = Config.BASE_URL
        const val TOKEN = Config.API_TOKEN
    }
}