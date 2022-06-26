package com.example.stockmarket.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockmarket.presentation.viewmodel.MainViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel()
) {
    val currenciesState = mainViewModel.currenciesState

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (currenciesState.isLoading) { // Loading
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                color = colors.secondary,
                strokeWidth = 6.dp
            )
        } else if (currenciesState.error.isNullOrEmpty()) { // Success
            val data = currenciesState.currencies
            if (data.isEmpty()) {
                Text(text = "List is empty.")
            } else LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(data.size) { pos ->
                    CurrencyItem(
                        data[pos],
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        } else { // Error
            Text(text = currenciesState.error ?: "Error")
        }
    }

}