package com.example.stockmarket.presentation.historical_info

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.presentation.LoadState
import com.example.stockmarket.presentation.common.OnError
import com.example.stockmarket.presentation.common.OnLoading
import kotlinx.coroutines.Job

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoricalInfoScreen(
    navController: NavController,
    currency: Currency,
    viewModel: HistoricalInfoViewModel = hiltViewModel()
) {
    val dataLoader: () -> Job = { viewModel.loadHistoricalData(currency.code) }
    remember { dataLoader.invoke() }
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = currency.name, fontSize = 20.sp)
            }
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = currency.symbol, fontSize = 20.sp)
            }
        }


            when (state) {
                is LoadState.Success -> if(state.data.isNotEmpty()) HistoryChart(state.data.take(5))
                is LoadState.Error -> OnError(state.message) { dataLoader.invoke() }
                is LoadState.Loading -> OnLoading()
                is LoadState.Refreshing -> OnLoading()
                is LoadState.Empty -> Unit
            }

    }
}