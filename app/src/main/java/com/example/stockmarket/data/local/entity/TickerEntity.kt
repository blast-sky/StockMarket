package com.example.stockmarket.data.local.entity

import androidx.room.*

@Entity(
    tableName = "ticker",
    indices = [Index("symbol", unique = true)]
)
data class TickerEntity(
    @[PrimaryKey ColumnInfo(name = "name")] val name: String,
    @ColumnInfo(name = "symbol") val symbol: String,
    @Embedded val stockExchange: StockExchangeEntity
)