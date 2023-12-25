package com.example.calculator.infrastructure.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.infrastructure.state.CalculatorViewModel

@Composable
fun SwitchSection(
    calculator: CalculatorViewModel = viewModel()
) {
    var isActive by remember { mutableStateOf(false) }

    val state by calculator.calculatorState.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Switch(checked = isActive, onCheckedChange = {
            isActive = it
            calculator.changeMod()
        } )
        Spacer(modifier = Modifier.width(10.0.dp))
        if(state.isLoading) CircularProgressIndicator()
    }
}