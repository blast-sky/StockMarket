package com.example.stockmarket.presentation.tab_item

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.example.stockmarket.R
import com.example.stockmarket.presentation.currency_list.CurrencyListScreen
import com.example.stockmarket.presentation.ticker_list.TickerListScreen

sealed class TabItem(@StringRes val title: Int, val content: @Composable () -> Unit) {
    object TickerItem : TabItem(R.string.tickers, { TickerListScreen() })
    object CurrencyItem : TabItem(R.string.currencies, { CurrencyListScreen() })
}