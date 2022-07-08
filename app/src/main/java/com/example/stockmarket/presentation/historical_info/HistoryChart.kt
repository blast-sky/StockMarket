package com.example.stockmarket.presentation.historical_info

import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.PathEffect
import android.graphics.Rect
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toComposePathEffect
import androidx.compose.ui.graphics.vector.DefaultStrokeLineCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.presentation.PreviewModel

@Preview
@Composable
fun HistoryChartPreview() {
    HistoryChart(listOf(PreviewModel.historicalData, PreviewModel.historicalData))
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryChart(data: List<HistoricalData>) {
    if (data.isEmpty()) return

    val min = data.minOf { it.low }
    val max = data.maxOf { it.high }

    val textPaint = Paint().apply {
        color = android.graphics.Color.BLACK
        textAlign = Paint.Align.CENTER
        textSize = with(LocalDensity.current) { 16.dp.toPx() }
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(30.dp)
    ) {
        val height = size.height
        val width = size.width

        val spaceBetweenPoint = width / data.size
        val spaceBetweenPrice = height / (data.size - 1)

        drawCircles(data)

        data.forEachIndexed { index, info ->
            drawContext.canvas.nativeCanvas.drawText(
                info.date.dayOfMonth.toString(),
                index * spaceBetweenPoint + spaceBetweenPoint / 2,
                height + 55f,
                textPaint
            )

            val price = (max - min) * (1 - getRatio(
                index * spaceBetweenPrice.toDouble(),
                height.toDouble(),
                0.0
            )) + min
            val heightNow = (height * (1 - getRatio(price - min, max, min))).toFloat()

            val priceString = price.toString()
            val formatPriceString = priceString.indexOf('.').let {
                var right =
                    if (it > 4) it
                    else it + 3
                if (right > priceString.length) right = priceString.length
                priceString.substring(0, right)
            }
            val bounds = Rect()
            textPaint.getTextBounds(formatPriceString, 0, formatPriceString.length, bounds)
            drawContext.canvas.nativeCanvas.drawText(
                formatPriceString,
                -16f,
                heightNow + bounds.height() / 2,
                textPaint
            )

            drawLine(
                color = Color.Magenta,
                start = Offset(55f, heightNow),
                end = Offset(width, heightNow),
                strokeWidth = 3f,
                pathEffect = DashPathEffect(floatArrayOf(5f, 5f), 2f)
                    .toComposePathEffect()
            )
        }
    }
}

private fun DrawScope.drawCircles(data: List<HistoricalData>) {
    val circleRadius = 14f

    val max = data.maxOf { it.high }
    val min = data.minOf { it.low }

    val height = size.height
    val width = size.width

    val spaceBetweenPoint = width / data.size

    data.forEachIndexed { index, info ->
        val low = info.low
        val high = info.high

        drawCircle(
            Color.Red,
            circleRadius,
            Offset(
                index * spaceBetweenPoint + spaceBetweenPoint / 2,
                (height * (1 - getRatio(low - min, max, min))).toFloat(),
            )
        )

        drawCircle(
            Color.Green,
            circleRadius,
            Offset(
                index * spaceBetweenPoint + spaceBetweenPoint / 2,
                (height * (1 - getRatio(high - min, max, min))).toFloat(),
            )
        )
    }
}

private fun getRatio(value: Double, from: Double, to: Double): Double = value / (from - to)