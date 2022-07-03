package com.example.stockmarket.presentation.routing

import com.example.stockmarket.presentation.currency_list.CurrencyListScreen

object CurrencyListRoute : Route(
    route = "CurrencyListRoute.toString()",
    content = { _, navController ->
        CurrencyListScreen(navController)
    }
)