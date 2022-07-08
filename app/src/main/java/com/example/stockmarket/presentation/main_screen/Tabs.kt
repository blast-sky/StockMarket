package com.example.stockmarket.presentation.main_screen

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarket.presentation.tab_item.TabItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier.defaultMinSize(minHeight = 40.dp),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(
                    pagerState,
                    tabPositions
                )
            )
        },
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch { pagerState.scrollToPage(index) }
                }
            ) {
                Text(text = stringResource(id = tab.title), fontSize = 25.sp)
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTabs() {
    val tabs = listOf(
        TabItem.TickerItem,
        TabItem.CurrencyItem
    )
    val state = rememberPagerState(pageCount = tabs.size)

    Tabs(tabs, state)
}
