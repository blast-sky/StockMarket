package com.example.stockmarket.domain.repository

import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.model.HistoricalData

interface LocalStockRepository: RemoteStockRepository {

    suspend fun insertCurrencies(currencies: List<Currency>)

    suspend fun insertHistoricalData(data: List<HistoricalData>)
}