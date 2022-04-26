package com.example.jetpackcomposecanvasexamples.presentation.main.drawerMenu.textTransform

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.nativeCanvas

@Composable
fun TextTransformScreen() {

    val primaryVariant = MaterialTheme.colors.primaryVariant.hashCode()

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawContext.canvas.nativeCanvas.apply {
            drawText(
                "Canvas text example",
                100f,
                100f,
                Paint().apply {
                    color = primaryVariant
                    textSize = 100f
                }
            )
        }
    }

}
