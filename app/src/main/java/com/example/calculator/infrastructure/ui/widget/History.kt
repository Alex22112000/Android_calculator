package com.example.calculator.infrastructure.ui.widget

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.infrastructure.state.CalculatorViewModel

@Composable
fun History(
    calculator: CalculatorViewModel = viewModel()
) {
    val state by calculator.calculatorState.collectAsState()
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        state.history.map {
            Row {
                Text(text = it.input)
                Text(text = " = ")
                Text(text = it.output)
            }

        }
    }
}