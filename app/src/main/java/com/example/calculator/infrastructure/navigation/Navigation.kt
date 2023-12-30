package com.example.calculator.infrastructure.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.calculator.infrastructure.Calculator
import com.example.calculator.infrastructure.ui.widget.History

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = "main") {
        composable("main") { Calculator(navController) }
        composable("history") { History() }
    }
}