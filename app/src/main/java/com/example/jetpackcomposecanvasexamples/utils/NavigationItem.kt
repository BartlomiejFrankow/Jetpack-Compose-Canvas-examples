package com.example.jetpackcomposecanvasexamples.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackcomposecanvasexamples.R

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: Int) {
    object DrawShapes : NavigationItem("draw_shapes", Icons.Default.Edit, R.string.draw_shapes)
    object CircleGame : NavigationItem("circle_game", Icons.Default.Edit, R.string.circle_game)
    object Clock : NavigationItem("clock", Icons.Default.Edit, R.string.clock)
    object WeightMeasurement : NavigationItem("weight_measurement", Icons.Default.Edit, R.string.weight_measurement)
}
