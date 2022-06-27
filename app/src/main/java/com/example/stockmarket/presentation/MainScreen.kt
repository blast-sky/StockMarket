package com.example.stockmarket.presentation

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockmarket.presentation.state.ListState
import com.example.stockmarket.presentation.viewmodel.MainViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel()
) {
    val currenciesState = mainViewModel.currenciesState

    SwipeRefresh(
        state = rememberSwipeRefreshState(currenciesState is ListState.Refreshing),
        onRefresh = { mainViewModel.refresh() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currenciesState) {
                is ListState.Loading -> {
                    CircularProgressIndicator(color = colors.onBackground)
                }
                is ListState.Success -> {
                    val data = currenciesState.data

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
                                )
                            }
                        }
                    }
                }
                is ListState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = currenciesState.message,
                            fontSize = 16.sp,
                        )
                        Button(onClick = { mainViewModel.loadCurrencies() }) {
                            Text(text = "Reload")
                        }
                    }

                }
                is ListState.Empty -> {
                    Text(
                        text = "Nothing happened.",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
                is ListState.Refreshing -> Unit
            }
        }
    }
}