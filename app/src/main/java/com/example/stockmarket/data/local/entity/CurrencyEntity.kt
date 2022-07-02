package com.example.stockmarket.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "currency",
    indices = [Index("code", unique = true)]
)
data class CurrencyEntity(
    @[PrimaryKey ColumnInfo(name = "symbol")] val symbol: String,
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "native") val symbolNative: String?,
    @ColumnInfo(name = "last_update") val lastUpdate: LocalDateTime = LocalDateTime.now()
)
