package com.example.stockmarket.presentation.currency_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.presentation.routing.HistoricalInfoRoute
import com.example.stockmarket.presentation.LoadState
import com.example.stockmarket.presentation.common.OnError
import com.example.stockmarket.presentation.common.OnLoading
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.gson.Gson

@Composable
fun CurrencyList(
    navController: NavController,
    currencyListViewModel: CurrencyListViewModel = hiltViewModel()
) {
    val currenciesState = currencyListViewModel.state

    SwipeRefresh(
        state = rememberSwipeRefreshState(currenciesState is LoadState.Refreshing),
        onRefresh = { currencyListViewModel.refresh() }
    ) {
        when (currenciesState) {
            is LoadState.Loading -> OnLoading()
            is LoadState.Success -> OnSuccess(
                currenciesState.data
            ) { pos ->
                val currency = currenciesState.data[pos]
                val currencyJson = Gson().toJson(currency)
                navController.navigate("${HistoricalInfoRoute.baseRoute}/$currencyJson")
            }
            is LoadState.Error -> OnError(
                currenciesState.message
            ) { currencyListViewModel.loadCurrencies() }
            is LoadState.Empty -> Text(
                text = "Nothing happened.",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            is LoadState.Refreshing -> Unit
        }
    }
}

@Composable
private fun OnSuccess(
    data: List<Currency>,
    onClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        if (data.isEmpty()) {
            item {
                Text(
                    text = "List is empty.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }
        } else {
            items(data.size) { pos ->
                CurrencyItem(
                    data[pos],
                    Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .clickable(onClick = { onClick(pos) })
                )
            }
        }
    }
}