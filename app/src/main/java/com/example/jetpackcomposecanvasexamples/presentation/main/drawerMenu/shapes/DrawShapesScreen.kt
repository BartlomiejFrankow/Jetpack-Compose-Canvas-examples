package com.example.jetpackcomposecanvasexamples.presentation.main.drawerMenu.shapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun DrawShapesScreen() {
    Canvas(
        modifier = Modifier
            .padding(16.dp)
            .size(360.dp)
    ) {
        drawRect(
            color = Color.Black,
            size = size
        )

        drawRect(
            color = Color.Red,
            topLeft = Offset(100f, 100f),
            size = Size(100f, 100f),
            style = Stroke(width = 3.dp.toPx())
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.Black, Color.Blue),
                center = center,
                radius = 100f
            ),
            radius = 100f,
            center = center
        )

        drawArc(
            color = Color.Green,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(100f, 500f),
            size = Size(200f, 200f),
            style = Stroke(width = 3.dp.toPx())
        )

        drawOval(
            color = Color.Magenta,
            topLeft = Offset(700f, 100f),
            size = Size(200f, 300f)
        )

        drawLine(
            color = Color.Cyan,
            start = Offset(200f, 800f),
            end = Offset(800f, 900f),
            strokeWidth = 5.dp.toPx()
        )
    }
}
