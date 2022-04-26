package com.example.jetpackcomposecanvasexamples.presentation.main.drawerMenu.circleClickGame

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecanvasexamples.R
import kotlinx.coroutines.delay
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.random.Random

private const val ONE_SECOND = 1000

@Composable
fun CircleGameScreen() {

    var points by remember { mutableStateOf(0) }
    var isTimerRunning by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.points, points),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Button(onClick = {
                isTimerRunning = !isTimerRunning
                points = 0
            }) {
                Text(text = if (isTimerRunning) stringResource(R.string.reset) else stringResource(R.string.start))
            }

            CountdownTimer(isTimerRunning = isTimerRunning) {
                isTimerRunning = false
            }
        }

        BallClicker(enabled = isTimerRunning) {
            points++
        }
    }
}

@Composable
fun CountdownTimer(
    time: Int = 30 * ONE_SECOND,
    isTimerRunning: Boolean = false,
    onTimerEnd: () -> Unit = { }
) {
    var currentTime by remember {
        mutableStateOf(time)
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (!isTimerRunning) {
            currentTime = time
            return@LaunchedEffect
        }

        if (currentTime > 0) {
            delay(ONE_SECOND.toLong())
            currentTime -= ONE_SECOND
        } else {
            onTimerEnd()
        }
    }

    Text(
        text = (currentTime / ONE_SECOND).toString(),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BallClicker(
    radius: Float = 100f,
    enabled: Boolean = false,
    color: Color = MaterialTheme.colors.primaryVariant,
    onBallClick: () -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        var ballPosition by remember {
            mutableStateOf(randomOffset(radius, constraints.maxWidth, constraints.maxHeight))
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(enabled) {
                    if (!enabled) return@pointerInput

                    detectTapGestures { offset ->
                        // √ a2 + b2
                        val distance = sqrt((offset.x - ballPosition.x).pow(2) + (offset.y - ballPosition.y).pow(2))

                        if (distance <= radius) {
                            ballPosition = randomOffset(radius, constraints.maxWidth, constraints.maxHeight)
                            onBallClick()
                        }
                    }
                }
        ) {
            drawCircle(
                color = color,
                radius = radius,
                center = ballPosition
            )
        }
    }
}

private fun randomOffset(radius: Float, width: Int, height: Int) = Offset(
    x = Random.nextInt(radius.roundToInt(), width - radius.roundToInt()).toFloat(),
    y = Random.nextInt(radius.roundToInt(), height - radius.roundToInt()).toFloat()
)
