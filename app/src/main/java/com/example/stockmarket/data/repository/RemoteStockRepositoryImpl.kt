package com.example.stockmarket.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.stockmarket.data.mapper.toCurrency
import com.example.stockmarket.data.mapper.toHistoricalData
import com.example.stockmarket.data.mapper.toTicker
import com.example.stockmarket.data.remote.StockMarketService
import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.domain.model.Ticker
import com.example.stockmarket.domain.repository.RemoteStockRepository
import com.example.stockmarket.util.Resource
import javax.inject.Inject

class RemoteStockRepositoryImpl @Inject constructor(
    private val service: StockMarketService
) : RemoteStockRepository {

    override suspend fun getCurrencies(): Resource<List<Currency>> {
        val response = try {
            service.getCurrencies()
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "Remote loading error")
        }
        return if (response.isSuccessful)
            Resource.Success(response.body()!!.currencies.map { it.toCurrency() })
        else
            Resource.Error("Response with code ${response.code()}")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getHistoricalData(symbol: String): Resource<List<HistoricalData>> {
        val response = try {
            service.getHistoricalData(symbols = symbol)
        } catch(e: Exception) {
            return Resource.Error(e.message ?: "Remote loading error")
        }
        return if (response.isSuccessful)
            Resource.Success(response.body()!!.historicalData.map { it.toHistoricalData() })
        else
            Resource.Error("Response with code ${response.code()}")
    }

    override suspend fun getTickers(): Resource<List<Ticker>> {
        val response = try {
            service.getTickers()
        } catch(e: Exception) {
            return Resource.Error(e.message ?: "Remote loading error")
        }
        return if (response.isSuccessful)
            Resource.Success(response.body()!!.tickers.map { it.toTicker() })
        else
            Resource.Error("Response with code ${response.code()}")
    }


}