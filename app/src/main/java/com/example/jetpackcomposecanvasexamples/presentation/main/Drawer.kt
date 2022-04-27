package com.example.jetpackcomposecanvasexamples.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpackcomposecanvasexamples.R
import com.example.jetpackcomposecanvasexamples.utils.NavigationItem.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {

    val menuItems = listOf(
        DrawShapes,
        CircleGame,
        TextTransform,
        WeightMeasurement
    )

    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = R.drawable.canvas_logo),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    menuItems.forEach { item ->
        DrawerItem(item = item, selected = currentRoute == item.route, onItemClick = {
            navController.navigate(item.route) {
                navController.graph.startDestinationRoute?.let { route ->
                    popUpTo(route) { saveState = true }
                }
                launchSingleTop = true
                restoreState = true
            }

            scope.launch {
                scaffoldState.drawerState.close()
            }
        })
    }

}
