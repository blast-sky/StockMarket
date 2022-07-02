package com.example.stockmarket.presentation.routing

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.navArgument
import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.presentation.historical_info.HistoricalInfoScreen


object HistoricalInfoRoute : Route(
    baseRoute = "info",
    route = "info/{currency}",
    arguments = listOf(navArgument("currency") { type = CurrencyNavType }),
    content = { entry, navController ->
        val args = requireNotNull(entry.arguments)
        val data = requireNotNull(args.getParcelable<Currency>("currency"))
        HistoricalInfoScreen(navController, data)
    }
)