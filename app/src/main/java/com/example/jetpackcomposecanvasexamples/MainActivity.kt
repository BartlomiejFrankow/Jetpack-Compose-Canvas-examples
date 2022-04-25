package com.example.jetpackcomposecanvasexamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.jetpackcomposecanvasexamples.presentation.main.ContentScreen
import com.example.jetpackcomposecanvasexamples.ui.theme.JetpackComposeCanvasExamplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCanvasExamplesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    ContentScreen()
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    JetpackComposeCanvasExamplesTheme {
//    }
//}
