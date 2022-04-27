package com.example.jetpackcomposecanvasexamples.presentation.main.drawerMenu.weightMeasurement

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecanvasexamples.presentation.main.drawerMenu.weightMeasurement.LineType.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private const val SHADOW_ALPHA = 50
private const val SHADOW_RADIUS = 60f

@Composable
fun WeightMeasurementScreen(
    modifier: Modifier = Modifier,
    style: ScaleStyle = ScaleStyle(),
    minWeight: Int = 0,
    maxWeight: Int = 200,
    initialWeight: Int = 80
) {
    var center by remember {
        mutableStateOf(Offset.Zero)
    }
    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }
    var angle by remember {
        mutableStateOf(0f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            center = this.center
            circleCenter = Offset(
                x = center.x,
                y = (style.scaleWidth.toPx() / 2f) + style.radius.toPx()
            )
            val outerRadius = style.radius.toPx() + (style.scaleWidth.toPx() / 2f)
            val innerRadius = style.radius.toPx() - (style.scaleWidth.toPx() / 2f)

            drawContext.canvas.nativeCanvas.apply {
                drawCircle(
                    circleCenter.x,
                    circleCenter.y,
                    style.radius.toPx(),
                    Paint().apply {
                        strokeWidth = style.scaleWidth.toPx()
                        color = Color.WHITE
                        setStyle(Paint.Style.STROKE)
                        setShadowLayer(SHADOW_RADIUS, 0f, 0f, Color.argb(SHADOW_ALPHA, 0, 0, 0))
                    }
                )
            } // the end of native canvas

            // draw lines
            for (currentWeight in minWeight..maxWeight) {
                val angleInRadians = (currentWeight - initialWeight + angle - 90).toRadians()
                val lineType = getLineType(currentWeight)
                val lineLength = getLineLength(lineType, style).toPx()
                val lineColor = getLineColor(lineType, style)
                val lineTop = Offset(
                    x = outerRadius * cos(angleInRadians) + circleCenter.x,
                    y = outerRadius * sin(angleInRadians) + circleCenter.y
                )
                val lineBottom = Offset(
                    x = (outerRadius - lineLength) * cos(angleInRadians) + circleCenter.x,
                    y = (outerRadius - lineLength) * sin(angleInRadians) + circleCenter.y
                )

                drawLine(
                    color = lineColor,
                    start = lineTop,
                    end = lineBottom,
                    strokeWidth = 1.dp.toPx()
                )
            }

        } // the end of compose canvas

    } // the end of box

}

private fun getLineType(currentWeight: Int) = when {
    currentWeight % 10 == 0 -> TenStep
    currentWeight % 5 == 0 -> FiveStep
    else -> NormalStep
}

private fun getLineLength(lineType: LineType, style: ScaleStyle) = when (lineType) {
    FiveStep -> style.fiveStepLineLength
    NormalStep -> style.normalLineLength
    TenStep -> style.tenStepLineLength
}

private fun getLineColor(lineType: LineType, style: ScaleStyle) = when (lineType) {
    FiveStep -> style.fiveStepLineColor
    NormalStep -> style.normalLineColor
    TenStep -> style.tenStepLineColor
}

private fun Float.toRadians() = this * (PI / 180f).toFloat()
