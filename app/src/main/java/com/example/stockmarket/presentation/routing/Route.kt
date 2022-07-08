package com.example.stockmarket.presentation.routing

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable


sealed class Route(
    val route: String,
    val baseRoute: String = route,
    val arguments: List<NamedNavArgument> = emptyList(),
    val deepLinks: List<NavDeepLink> = emptyList(),
    val content: @Composable (NavBackStackEntry, NavController) -> Unit
) {

    fun makeComposable(builder: NavGraphBuilder, navController: NavController) {
        builder.composable(
            route = route,
            arguments = arguments,
            deepLinks = deepLinks,
            content = { backStack -> content(backStack, navController) }
        )
    }
}






