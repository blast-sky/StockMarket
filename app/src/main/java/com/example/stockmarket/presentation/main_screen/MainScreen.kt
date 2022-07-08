package com.example.stockmarket.presentation.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.stockmarket.presentation.tab_item.TabItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen() {
    val tabs = listOf(
        TabItem.TickerItem,
        TabItem.CurrencyItem
    )
    val pagerState = rememberPagerState(pageCount = tabs.size)
    val navController = rememberNavController()

    Column(modifier = Modifier.fillMaxSize()) {
        Tabs(tabs, pagerState)
        TabsContent(
            tabs = tabs,
            pagerState = pagerState,
            navController = navController
        )
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}