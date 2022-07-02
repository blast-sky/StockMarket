package com.example.stockmarket.presentation.routing

import com.example.stockmarket.presentation.currency_list.MainScreen

object MainRoute : Route(
    route = "main",
    content = { _, navController ->
        MainScreen(navController)
    }
)