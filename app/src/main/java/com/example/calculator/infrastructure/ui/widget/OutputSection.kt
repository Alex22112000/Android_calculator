package com.example.calculator.infrastructure.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.infrastructure.state.CalculatorViewModel

@Preview
@Composable
fun OutputSection(
        calculator: CalculatorViewModel = viewModel()
) {
        val state by calculator.calculatorState.collectAsState()

        LazyColumn(
                modifier = Modifier.fillMaxWidth()
        ) {
                item {
                        Text(
                                text = state.input,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 0.dp, horizontal = 8.dp),
                                fontWeight = FontWeight.Light,
                                fontSize = 40.sp,
                                color = Color.White,
                                maxLines = 1
                        )
                        Text(
                                text = state.output,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 0.dp, horizontal = 8.dp),
                                fontWeight = FontWeight.Light,
                                fontSize = 80.sp,
                                color = Color.White,
                                maxLines = 1
                        )

                }
        }
}