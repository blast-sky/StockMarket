package com.example.stockmarket.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.stockmarket.data.remote.pojo.Currency


@Composable
fun CurrencyItem(
    currency: Currency,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(3f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = currency.name, fontSize = 20.sp)
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = currency.symbolNative, fontSize = 20.sp)
        }
    }
}