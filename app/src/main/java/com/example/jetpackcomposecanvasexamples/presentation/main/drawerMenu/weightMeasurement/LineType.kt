package com.example.jetpackcomposecanvasexamples.presentation.main.drawerMenu.weightMeasurement

sealed class LineType {
    object NormalStep : LineType()
    object FiveStep : LineType()
    object TenStep : LineType()
}
