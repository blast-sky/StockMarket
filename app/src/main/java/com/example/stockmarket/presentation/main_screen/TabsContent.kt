package com.example.stockmarket.presentation.main_screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.stockmarket.presentation.tab_item.TabItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerDefaults
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(
    tabs: List<TabItem>,
    pagerState: PagerState,
    navController: NavController,
) {
    HorizontalPager(
        state = pagerState,
        flingBehavior = PagerDefaults.defaultPagerFlingConfig(
            pagerState,
            rememberSplineBasedDecay(),
            spring(stiffness = Spring.StiffnessLow)
        )
    ) { page ->
        tabs[page].content()
    }
}