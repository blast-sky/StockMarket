package com.example.stockmarket.presentation.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OnEmpty() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        text = "List is empty",
        style = MaterialTheme.typography.subtitle2,
        textAlign = TextAlign.Center
    )
}