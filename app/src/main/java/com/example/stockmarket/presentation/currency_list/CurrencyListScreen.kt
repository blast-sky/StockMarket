package com.example.stockmarket.presentation.currency_list

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.stockmarket.presentation.routing.CurrencyListRoute
import com.example.stockmarket.presentation.routing.HistoricalInfoRoute

@Composable
fun CurrencyListScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CurrencyListRoute.route) {
        CurrencyListRoute.makeComposable(this, navController)
        HistoricalInfoRoute.makeComposable(this, navController)
    }
}