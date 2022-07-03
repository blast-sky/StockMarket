package com.example.stockmarket.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.stockmarket.data.remote.dto.HistoricalDataDto
import com.example.stockmarket.domain.model.HistoricalData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun HistoricalDataDto.toHistoricalData() : HistoricalData {
    val pattern = "yyyy-MM-dd'T'00:00:00+0000"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val date = LocalDate.parse(this.date, formatter)
    return HistoricalData(
        date = date,
        symbol = this.symbol,
        high = this.high,
        low = this.low,
        exchange = this.exchange
    )
}