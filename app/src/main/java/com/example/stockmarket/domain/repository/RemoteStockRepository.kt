package com.example.stockmarket.domain.repository

import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.domain.model.Ticker
import com.example.stockmarket.util.Resource
import java.text.DateFormatSymbols

interface RemoteStockRepository {

    suspend fun getCurrencies(): Resource<List<Currency>>

    suspend fun getHistoricalData(symbol: String): Resource<List<HistoricalData>>

    suspend fun getTickers(): Resource<List<Ticker>>
}