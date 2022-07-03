package com.example.stockmarket.presentation.ticker_list

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.domain.model.Ticker
import com.example.stockmarket.presentation.LoadState
import com.example.stockmarket.presentation.common.OnError
import com.example.stockmarket.presentation.common.OnLoading
import com.example.stockmarket.presentation.historical_info.HistoryChart

@Composable
fun TickerItem(
    ticker: Ticker,
    expanded: String,
    changeExpanded: (String) -> Unit,
    viewModel: TickerListViewModel = hiltViewModel()
) {
    val shape = MaterialTheme.shapes.medium
    val color = MaterialTheme.colors.surface
    val borderColor = MaterialTheme.colors.secondary

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(true, 1000.dp)
            ) { changeExpanded.invoke(ticker.symbol) }
            .animateContentSize(),
        shape = shape,
        elevation = 4.dp,
        backgroundColor = color,
        border = BorderStroke(2.dp, borderColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = ticker.name, style = MaterialTheme.typography.h5)
                Text(text = ticker.symbol, style = MaterialTheme.typography.h6)
            }

            if (expanded == ticker.symbol) {
                Divider(
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth(0.6f),
                    thickness = 2.dp
                )

                remember { viewModel.loadHistoricalData(ticker.symbol) }
                when (val state = viewModel.historyDataProvider.state) {
                    is LoadState.Loading -> OnLoading()
                    is LoadState.Error -> OnError(state.message) {
                        viewModel.loadHistoricalData(ticker.symbol)
                    }
                    is LoadState.Empty -> changeExpanded.invoke("")
                    is LoadState.Refreshing -> Unit
                    is LoadState.Success ->
                        if (state.data.isEmpty())
                            changeExpanded.invoke("")
                        else
                            HistoryChart(state.data.take(5))
                }
            }
        }
    }
}