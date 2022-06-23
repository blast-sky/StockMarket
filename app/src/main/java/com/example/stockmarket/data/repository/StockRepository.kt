package com.example.stockmarket.data.repository

import com.example.stockmarket.data.remote.pojo.Currency
import com.example.stockmarket.util.Resource

interface StockRepository {

    suspend fun getCurrencies(): Resource<List<Currency>>
}