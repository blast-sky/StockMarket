package com.example.stockmarket.domain.repository

import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.util.Resource

interface StockRepository {

    suspend fun getCurrencies(): Resource<List<Currency>>

    suspend fun getHistoricalData(): Resource<List<HistoricalData>>
}