package com.example.stockmarket.presentation.routing

import com.example.stockmarket.presentation.ticker_list.TickerListScreen

object TickerListRoute : Route(
  route = "TickerListRoute",
  content = { _, controller ->
      TickerListScreen(navController = controller)
  }
)