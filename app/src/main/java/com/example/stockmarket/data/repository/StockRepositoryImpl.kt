package com.example.stockmarket.data.repository

import com.example.stockmarket.data.remote.StockMarketService
import com.example.stockmarket.data.remote.pojo.Currency
import com.example.stockmarket.domain.repository.StockRepository
import com.example.stockmarket.util.Resource
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val service: StockMarketService
) : StockRepository {

    override suspend fun getCurrencies(): Resource<List<Currency>> {
        val response = try {
            service.getCurrencies()
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "Remote loading Error 1")
        }
        return if (response.isSuccessful)
            Resource.Success(response.body()!!.currencies)
        else
            Resource.Error("Remote loading Error 2")
    }
}