package com.example.stockmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.stockmarket.presentation.routing.HistoricalInfoRoute
import com.example.stockmarket.presentation.routing.MainRoute
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
                systemUiController.setSystemBarsColor(
                    MaterialTheme.colors.background,
                    darkIcons = true
                )

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = MainRoute.route) {
                    MainRoute.makeComposable(this, navController)
                    HistoricalInfoRoute.makeComposable(this, navController)
                }
            }
        }
    }
}