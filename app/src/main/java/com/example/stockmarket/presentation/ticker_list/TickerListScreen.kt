package com.example.stockmarket.presentation.ticker_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.stockmarket.domain.model.Ticker
import com.example.stockmarket.presentation.LoadState
import com.example.stockmarket.presentation.common.OnEmpty
import com.example.stockmarket.presentation.common.OnError
import com.example.stockmarket.presentation.common.OnLoading
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun TickerListScreen(
    navController: NavController,
    viewModel: TickerListViewModel = hiltViewModel()
) {
    val state = viewModel.tickersProvider.state
    var expanded by remember { mutableStateOf("") }

    SwipeRefresh(
        state = rememberSwipeRefreshState(state is LoadState.Refreshing),
        onRefresh = { viewModel.refreshTickers() }
    ) {
        when (state) {
            is LoadState.Loading -> OnLoading()
            is LoadState.Error -> OnError(state.message) { viewModel.loadTickers() }
            is LoadState.Empty -> OnEmpty()
            is LoadState.Refreshing -> Unit
            is LoadState.Success -> OnSuccess(state.data, expanded) { symbols ->
                expanded = symbols
            }
        }
    }
}

@Composable
fun OnSuccess(tickers: List<Ticker>, expanded: String, changeExpanded: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(tickers) { ticker -> TickerItem(ticker, expanded, changeExpanded) }
    }
}