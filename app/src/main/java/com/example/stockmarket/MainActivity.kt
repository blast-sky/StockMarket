package com.example.stockmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.example.stockmarket.presentation.main_screen.MainScreen
import com.example.stockmarket.ui.theme.StockMarketTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketTheme {
                val systemUiController = rememberSystemUiController()

                MainScreen()

//                NavHost(navController = navController, startDestination = TickerListRoute().route) {
//                    CurrencyListRoute.makeComposable(this, navController)
//                    HistoricalInfoRoute.makeComposable(this, navController)
//                    TickerListRoute().makeComposable(this, navController)
//                }
            }
        }
    }
}