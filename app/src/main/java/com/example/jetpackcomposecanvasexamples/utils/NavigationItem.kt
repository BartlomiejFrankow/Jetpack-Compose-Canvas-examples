package com.example.jetpackcomposecanvasexamples.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackcomposecanvasexamples.R

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: Int) {
    object DrawShapes : NavigationItem("draw_shapes", Icons.Default.Edit, R.string.draw_shapes)
    object Another : NavigationItem("another", Icons.Default.Edit, R.string.another)
}