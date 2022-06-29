package com.example.stockmarket.presentation.historical_info

import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import com.example.stockmarket.domain.model.HistoricalData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryChart(data: List<HistoricalData>) {
    require(data.isNotEmpty())

    val dividingK = 1.2
    val min = data.minOf { it.low }.div(dividingK)
    val max = data.maxOf { it.high }.times(dividingK)

    val textPaint = Paint().apply {
        color = android.graphics.Color.BLACK
        textAlign = Paint.Align.CENTER
        textSize = 10f
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val spaceBetweenText = size.width / (data.size + 1)

        data.forEachIndexed { index, info ->
            drawContext.canvas.nativeCanvas.drawText(
                info.date.dayOfMonth.toString(),
                index * spaceBetweenText,
                0f,
                textPaint
            )

            val spaceBetweenPrice = size.height / (data.size + 1)

            drawCircle(
                color = Color.Green,
                radius = 2f,
                center = Offset(
                    index * spaceBetweenText,
                    (spaceBetweenPrice + (size.height - spaceBetweenPrice) * (max - min) / info.low).toFloat()
                )
            )
        }
    }
}
