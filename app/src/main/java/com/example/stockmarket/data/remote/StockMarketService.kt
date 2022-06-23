package com.example.stockmarket.data.remote

import com.example.stockmarket.data.remote.pojo.PaginationAndData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StockMarketService {

    @GET("v1/currencies")
    suspend fun getCurrencies(
        @Query("access_key") token: String = TOKEN,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ) : Response<PaginationAndData>

    companion object {
        const val BASE_URL = "http://api.marketstack.com/"
        const val TOKEN = ""
    }
}