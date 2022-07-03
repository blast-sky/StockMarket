package com.example.stockmarket.data.repository

import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.domain.model.Ticker
import com.example.stockmarket.domain.repository.LocalStockRepository
import com.example.stockmarket.util.Resource
import javax.inject.Inject

class TestLocalStockRepository @Inject constructor() : LocalStockRepository {

    override suspend fun insertCurrencies(currencies: List<Currency>) = Unit

    override suspend fun insertHistoricalData(data: List<HistoricalData>) = Unit

    override suspend fun insertTickers(tickers: List<Ticker>) = Unit

    override suspend fun getCurrencies(): Resource<List<Currency>> =
        Resource.Error("Error from LocalTestRepository")

    override suspend fun getHistoricalData(symbol: String): Resource<List<HistoricalData>> =
        Resource.Error("Error from LocalTestRepository")

    override suspend fun getTickers(): Resource<List<Ticker>> =
        Resource.Error("Error from LocalTestRepository")
}