package com.example.jetpackcomposecanvasexamples.presentation.main.drawerMenu.clock

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecanvasexamples.presentation.main.drawerMenu.weightMeasurement.SHADOW_RADIUS
import com.example.jetpackcomposecanvasexamples.utils.isFifthStep
import com.example.jetpackcomposecanvasexamples.utils.toRadians
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

private const val START_TIME = 0
private const val END_TIME = 59
private const val FULL_CIRCLE_DEGREES = 360f
private const val MINUTES_IN_HOUR = 60f
private const val SECONDS_IN_MINUTE = 60f
private const val SECONDS_IN_MINUTE_TYPE_INT = 60
private const val HOURS_IN_DAY = 12f
private const val SECONDS_IN_HOUR = 3600f
private const val TIME_ZONE_DIFFERENCE = 2f
private const val CIRCLE_STEP_IN_DEGREES = (FULL_CIRCLE_DEGREES / MINUTES_IN_HOUR)
private const val MINUTE_OR_SECOND_LINE_LENGTH = 20
private const val HOUR_DISTANCE_FROM_CLOCK_EDGE = 35
private const val SECONDS_LINE_WIDTH = 1
private const val MINUTES_LINE_WIDTH = 2
private const val HOURS_LINE_WIDTH = 4
private const val ONE_SECOND = 1f
private const val ONE_SECOND_TYPE_LONG = 1000L
private const val ONE_SECOND_TYPE_FLOAT = 1000f

@Composable
fun ClockScreen(radius: Dp = 150.dp) {

    val milliseconds = remember {
        System.currentTimeMillis()
    }
    var seconds by remember {
        mutableStateOf((milliseconds / ONE_SECOND_TYPE_FLOAT) % SECONDS_IN_MINUTE)
    }
    var minutes by remember {
        mutableStateOf(((milliseconds / ONE_SECOND_TYPE_FLOAT) / SECONDS_IN_MINUTE_TYPE_INT) % MINUTES_IN_HOUR)
    }
    var hours by remember {
        mutableStateOf((milliseconds / ONE_SECOND_TYPE_FLOAT) / SECONDS_IN_HOUR + TIME_ZONE_DIFFERENCE)
    }

    LaunchedEffect(key1 = seconds) {
        delay(ONE_SECOND_TYPE_LONG)
        minutes += ONE_SECOND / SECONDS_IN_MINUTE
        hours += ONE_SECOND / (MINUTES_IN_HOUR * HOURS_IN_DAY)
        seconds += ONE_SECOND
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .size(radius * 2f)
                .align(Alignment.Center)
        ) {
            drawShadow(radius)

            // Lines
            for (index in START_TIME..END_TIME) {
                val angleInRad = index * (FULL_CIRCLE_DEGREES / MINUTES_IN_HOUR).toRadians()
                val lineLength = if (index.isFifthStep()) 20.dp.toPx() else 15.dp.toPx()
                val strokeWidth = if (index.isFifthStep()) 1.dp.toPx() else 0.5.dp.toPx()
                val color = if (index.isFifthStep()) Gray else LightGray

                drawClockShield(radius, angleInRad, lineLength, color, strokeWidth)
            }

            rotateSeconds(seconds)
            rotateMinutes(minutes)
            rotateHours(hours)
        }
    }
}

private fun DrawScope.rotateHours(hours: Float) {
    rotate(degrees = hours * (FULL_CIRCLE_DEGREES / HOURS_IN_DAY)) {
        drawLine(
            color = Color.Black,
            start = center,
            end = Offset(center.x, HOUR_DISTANCE_FROM_CLOCK_EDGE.dp.toPx()),
            strokeWidth = HOURS_LINE_WIDTH.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

private fun DrawScope.rotateMinutes(minutes: Float) {
    rotate(degrees = minutes * CIRCLE_STEP_IN_DEGREES) {
        drawLine(
            color = Color.Black,
            start = center,
            end = Offset(center.x, MINUTE_OR_SECOND_LINE_LENGTH.dp.toPx()),
            strokeWidth = MINUTES_LINE_WIDTH.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

private fun DrawScope.rotateSeconds(seconds: Float) {
    rotate(degrees = seconds * CIRCLE_STEP_IN_DEGREES) {
        drawLine(
            color = Color.Red,
            start = center,
            end = Offset(center.x, MINUTE_OR_SECOND_LINE_LENGTH.dp.toPx()),
            strokeWidth = SECONDS_LINE_WIDTH.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

private fun DrawScope.drawClockShield(radius: Dp, angleInRad: Float, lineLength: Float, color: Color, strokeWidth: Float) {
    val lineStart = Offset(
        x = radius.toPx() * cos(angleInRad) + center.x,
        y = radius.toPx() * sin(angleInRad) + center.y
    )
    val lineEnd = Offset(
        x = (radius.toPx() - lineLength) * cos(angleInRad) + center.x,
        y = (radius.toPx() - lineLength) * sin(angleInRad) + center.y
    )
    drawLine(
        color = color,
        start = lineStart,
        end = lineEnd,
        strokeWidth = strokeWidth
    )
}

private fun DrawScope.drawShadow(radius: Dp) {
    drawContext.canvas.nativeCanvas.apply {
        drawCircle(
            center.x,
            center.y,
            radius.toPx(),
            Paint().apply {
                color = android.graphics.Color.WHITE
                setShadowLayer(
                    SHADOW_RADIUS,
                    0f,
                    0f,
                    android.graphics.Color.argb(50, 0, 0, 0)
                )
            }
        )
    }
}
