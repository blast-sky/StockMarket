package com.example.stockmarket.data.repository

import com.example.stockmarket.data.local.ObsoleteRule
import com.example.stockmarket.data.local.StockDao
import com.example.stockmarket.data.local.mapper.toEntity
import com.example.stockmarket.data.local.mapper.toTicker
import com.example.stockmarket.data.mapper.toCurrency
import com.example.stockmarket.data.mapper.toEntity
import com.example.stockmarket.data.mapper.toHistoricalData
import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.domain.model.Ticker
import com.example.stockmarket.domain.repository.LocalStockRepository
import com.example.stockmarket.util.Resource
import java.time.LocalDateTime
import javax.inject.Inject

class LocalStockRepositoryImpl @Inject constructor(
    private val stockDao: StockDao,
    private val obsoleteRule: ObsoleteRule
) : LocalStockRepository {

    override suspend fun insertCurrencies(currencies: List<Currency>) =
        stockDao.insertCurrencies(currencies.map { it.toEntity() })

    override suspend fun insertHistoricalData(data: List<HistoricalData>) =
        stockDao.insertHistoricalDataEntities(data.map { it.toEntity() })

    override suspend fun insertTickers(tickers: List<Ticker>) =
        stockDao.insertTickers(tickers.map { it.toEntity() })


    override suspend fun getCurrencies(): Resource<List<Currency>> =
        loadAndCheckObsoleteData(
            mapper = { it.toCurrency() },
            getDate = { it.lastUpdate },
            loader = { stockDao.getCurrencies() }
        )

    override suspend fun getHistoricalData(symbol: String): Resource<List<HistoricalData>> =
        loadAndCheckObsoleteData(
            mapper = { it.toHistoricalData() },
            getDate = { it.lastUpdate },
            loader = { stockDao.getHistoricalDataForCurrencySymbol(symbol) }
        )

    override suspend fun getTickers(): Resource<List<Ticker>> =
        loadAndCheckObsoleteData(
            mapper = { it.toTicker() },
            getDate = { LocalDateTime.now() },
            loader = { stockDao.getTickers() }
        )

    private suspend fun <I, O> loadAndCheckObsoleteData(
        mapper: (I) -> O,
        getDate: (I) -> LocalDateTime,
        loader: suspend () -> List<I>
    ) = try {
        val data = loader.invoke()
        val hasObsolete =
            data.firstOrNull { obsoleteRule.isObsolete(getDate(it)) } != null || data.isEmpty()
        if (hasObsolete) Resource.Error("Data is obsolete.")
        else Resource.Success(data.map { mapper(it) })
    } catch (e: Exception) {
        Resource.Error("Can`t load data from database.")
    }
}