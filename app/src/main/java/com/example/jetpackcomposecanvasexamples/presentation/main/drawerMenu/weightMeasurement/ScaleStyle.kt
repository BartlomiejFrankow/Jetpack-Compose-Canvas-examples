package com.example.jetpackcomposecanvasexamples.presentation.main.drawerMenu.weightMeasurement

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecanvasexamples.ui.theme.Purple700

data class ScaleStyle(
    val scaleWidth: Dp = 160.dp,
    val radius: Dp = 550.dp,
    val normalLineColor: Color = Color.LightGray,
    val fiveStepLineColor: Color = Color.Green,
    val tenStepLineColor: Color = Purple700,
    val normalLineLength: Dp = 25.dp,
    val fiveStepLineLength: Dp = 35.dp,
    val tenStepLineLength: Dp = 50.dp,
    val scaleIndicatorColor: Color = Color.Green,
    val scaleIndicatorLength: Dp = 60.dp,
    val textSize: TextUnit = 18.sp
)
