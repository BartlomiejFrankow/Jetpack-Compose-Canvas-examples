package com.example.jetpackcomposecanvasexamples.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackcomposecanvasexamples.presentation.main.drawerMenu.circleClickGame.CircleGameScreen
import com.example.jetpackcomposecanvasexamples.presentation.main.drawerMenu.shapes.DrawShapesScreen
import com.example.jetpackcomposecanvasexamples.presentation.main.drawerMenu.textTransform.TextTransformScreen
import com.example.jetpackcomposecanvasexamples.utils.NavigationItem

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItem.DrawShapes.route) {
        composable(NavigationItem.DrawShapes.route) { DrawShapesScreen() }
        composable(NavigationItem.CircleGame.route) { CircleGameScreen() }
        composable(NavigationItem.TextTransform.route) { TextTransformScreen() }
    }
}
