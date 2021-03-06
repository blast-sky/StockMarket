package com.example.stockmarket.data.remote

import com.example.stockmarket.Config
import com.example.stockmarket.data.remote.dto.pagination.PaginationAndCurrenciesDto
import com.example.stockmarket.data.remote.dto.pagination.PaginationAndHistoricalDataDto
import com.example.stockmarket.data.remote.dto.pagination.PaginationAndTickers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StockMarketService {

    @GET("v1/currencies")
    suspend fun getCurrencies(
        @Query("access_key") token: String = TOKEN,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<PaginationAndCurrenciesDto>

    @GET("v1/eod")
    suspend fun getHistoricalData(
        @Query("access_key") token: String = TOKEN,
        @Query("symbols") symbols: String,
        @Query("date_from") date_from: String? = null,
        @Query("date_to") date_to: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<PaginationAndHistoricalDataDto>

    @GET("v1/tickers")
    suspend fun getTickers(
        @Query("access_key") token: String = TOKEN,
    ): Response<PaginationAndTickers>

    companion object {
        const val BASE_URL = Config.BASE_URL
        const val TOKEN = Config.API_TOKEN
    }
}