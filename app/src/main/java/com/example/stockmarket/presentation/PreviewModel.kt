package com.example.stockmarket.presentation

import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.domain.model.StockExchange
import com.example.stockmarket.domain.model.Ticker
import java.time.LocalDate

interface PreviewModel {
    companion object {
        val ticker =
            Ticker(
                name = "name",
                symbol = "symbol",
                stockExchange = StockExchange(name = "name"),
            )

        val historicalData = HistoricalData(
            date = LocalDate.now(),
            symbol = "symbol",
            exchange = "exchange",
            high = 10.0,
            low = 5.0,
        )
    }
}