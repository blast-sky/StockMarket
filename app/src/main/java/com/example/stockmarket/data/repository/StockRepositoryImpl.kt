package com.example.stockmarket.data.repository

import com.example.stockmarket.data.mapper.toCurrency
import com.example.stockmarket.data.remote.StockMarketService
import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.model.HistoricalData
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
            return Resource.Error(e.message ?: "Remote loading error")
        }
        return if (response.isSuccessful)
            Resource.Success(response.body()!!.currencies.map { it.toCurrency() })
        else
            Resource.Error("Response with code ${response.code()}")
    }

    override suspend fun getHistoricalData(): Resource<List<HistoricalData>> {
        TODO()
    }
}