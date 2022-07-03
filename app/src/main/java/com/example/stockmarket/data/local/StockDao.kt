package com.example.stockmarket.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.stockmarket.data.local.entity.CurrencyEntity
import com.example.stockmarket.data.local.entity.HistoricalDataEntity
import com.example.stockmarket.data.local.entity.TickerEntity

@Dao
interface StockDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertCurrencies(items: List<CurrencyEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertHistoricalDataEntities(items: List<HistoricalDataEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertTickers(items: List<TickerEntity>)

    @Query("SELECT * FROM historical_data WHERE symbol = :symbol")
    suspend fun getHistoricalDataForCurrencySymbol(symbol: String): List<HistoricalDataEntity>

    @Query("SELECT * FROM currency")
    suspend fun getCurrencies(): List<CurrencyEntity>

    @Query("SELECT * FROM ticker")
    suspend fun getTickers(): List<TickerEntity>
}