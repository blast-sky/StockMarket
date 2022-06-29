package com.example.stockmarket.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.domain.repository.StockRepository
import com.example.stockmarket.util.Resource
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getHistoricalData(): Resource<List<HistoricalData>> {
        delay(1 + Random.nextLong(2000))
        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd")
        return when (Random.nextUInt() % 3u) {
            0u -> Resource.Success(emptyList())
            1u -> Resource.Success(
                listOf(
                    HistoricalData(
                        LocalDateTime.parse("2022-06-27", formatter),
                        "do",
                        "S",
                        10.0,
                        20.0
                    ),
                    HistoricalData(
                        LocalDateTime.parse("2022-06-26", formatter),
                        "dol",
                        "Ss",
                        10.0,
                        20.0
                    ),
                    HistoricalData(
                        LocalDateTime.parse("2022-06-25", formatter),
                        "doll",
                        "Sz",
                        10.0,
                        20.0
                    ),
                    HistoricalData(
                        LocalDateTime.parse("2022-06-24", formatter),
                        "dollar",
                        "Sc",
                        10.0,
                        20.0
                    ),
                    HistoricalData(
                        LocalDateTime.parse("2022-06-23", formatter),
                        "dollar",
                        " Sc ",
                        10.0,
                        20.0
                    ),
                )
            )
            2u -> Resource.Error("Error from TestRepository")
            else -> Resource.Error("Error from TestRepository")
        }
    }
}