package com.example.stockmarket.data.repository

import com.example.stockmarket.data.remote.pojo.Currency
import com.example.stockmarket.domain.repository.StockRepository
import com.example.stockmarket.util.Resource
import javax.inject.Inject

class TestStockRepository @Inject constructor() : StockRepository {

    override suspend fun getCurrencies(): Resource<List<Currency>> {
        //return Resource.Error("CAvo")
        return Resource.Success(
            listOf(
                Currency("123", "do", "S", null),
                Currency("1234", "dol", "Ss", null),
                Currency("1235", "doll", "Sz", null),
                Currency("1236", "dollr", "Sc", null),
            )
        )
    }
}