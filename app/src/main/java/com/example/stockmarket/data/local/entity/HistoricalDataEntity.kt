package com.example.stockmarket.data.local.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.time.LocalDate
import java.time.LocalDateTime


@Entity(
    tableName = "historical_data",
    primaryKeys = ["symbol", "date"],
    foreignKeys = [ForeignKey(
        entity = TickerEntity::class,
        parentColumns = ["symbol"],
        childColumns = ["symbol"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class HistoricalDataEntity(
    @ColumnInfo(name = "date") val date: LocalDate,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "exchange") val exchange: String,
    @ColumnInfo(name = "high") val high: Double,
    @ColumnInfo(name = "low") val low: Double,
    @ColumnInfo(name = "last_update") val lastUpdate: LocalDateTime = LocalDateTime.now()
)