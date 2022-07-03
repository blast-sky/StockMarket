package com.example.stockmarket.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.stockmarket.data.local.converter.LocalDateConverter
import com.example.stockmarket.data.local.converter.LocalDateTimeConverter
import com.example.stockmarket.data.local.entity.CurrencyEntity
import com.example.stockmarket.data.local.entity.HistoricalDataEntity
import com.example.stockmarket.data.local.entity.TickerEntity

@Database(
    entities = [CurrencyEntity::class, HistoricalDataEntity::class, TickerEntity::class],
    version = 4
)
@TypeConverters(LocalDateTimeConverter::class, LocalDateConverter::class)
abstract class StockDataBase : RoomDatabase() {
    abstract fun stockMarketDao(): StockDao
}