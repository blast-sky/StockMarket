package com.example.stockmarket.domain.repository

import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.domain.model.Ticker

interface LocalStockRepository: RemoteStockRepository {

    suspend fun insertCurrencies(currencies: List<Currency>)

    suspend fun insertHistoricalData(data: List<HistoricalData>)

    suspend fun insertTickers(tickers: List<Ticker>)
}