package com.example.stockmarket.presentation.historical_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.model.HistoricalData

@Composable
fun HistoricalInfoScreen(navController: NavController, currency: Currency) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = currency.name)
        Divider(
            modifier = Modifier
                .padding(4.dp)
                .width(1.dp),
            thickness = 16.dp,
            startIndent = 0.dp,
            color = Color.Green
        )

        Text(text = currency.symbol)
    }
}