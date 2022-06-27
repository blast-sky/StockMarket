package com.example.stockmarket.data.repository

import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.domain.repository.StockRepository
import com.example.stockmarket.util.Resource
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextUInt

class TestStockRepository @Inject constructor() : StockRepository {

    override suspend fun getCurrencies(): Resource<List<Currency>> {
        delay(1 + Random.nextLong(2000))
        return when (Random.nextUInt() % 3u) {
            0u -> Resource.Success(emptyList())
            1u -> Resource.Success(
                listOf(
                    Currency("123", "do", "S", null),
                    Currency("1234", "dol", "Ss", null),
                    Currency("1235", "doll", "Sz", null),
                    Currency("1236", "dollar", "Sc", null),
                )
            )
            2u -> Resource.Error("Error from TestRepository")
            else -> Resource.Error("Error from TestRepository")
        }
    }

    override suspend fun getHistoricalData(): Resource<List<HistoricalData>> {
        delay(1 + Random.nextLong(2000))
        return when (Random.nextUInt() % 3u) {
            0u -> Resource.Success(emptyList())
            1u -> Resource.Success(
                listOf(
                    HistoricalData("2022-06-27", "do", "S", 10.0, 20.0),
                    HistoricalData("2022-06-26", "dol", "Ss", 10.0, 20.0),
                    HistoricalData("2022-06-25", "doll", "Sz", 10.0, 20.0),
                    HistoricalData("2022-06-24", "dollar", "Sc", 10.0, 20.0),
                    HistoricalData("2022-06-23", "dollar", "Sc", 10.0, 20.0),
                )
            )
            2u -> Resource.Error("Error from TestRepository")
            else -> Resource.Error("Error from TestRepository")
        }
    }
}