package com.example.stockmarket.data.repository

import com.example.stockmarket.data.remote.StockMarketService
import com.example.stockmarket.data.remote.pojo.Currency
import com.example.stockmarket.util.Resource
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val service: StockMarketService
) : StockRepository {

    override suspend fun getCurrencies(): Resource<List<Currency>> {
        val response = service.getCurrencies()
        return if(response.isSuccessful)
            Resource.Success(response.body()!!.currencies)
        else
            Resource.Error("Loading Error")
    }
}