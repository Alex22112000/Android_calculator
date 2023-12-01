package com.example.calculator.ui.widget

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.calculator.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.state.CalculatorViewModel

@Composable
fun InputSection(
    calculator: CalculatorViewModel = viewModel()
) {
    val buttons = remember { listOf(
        ".",
        "DEL",
        "C",
        "/",
        "1",
        "2",
        "3",
        "*",
        "4",
        "5",
        "6",
        "+",
        "7",
        "8",
        "9",
        "-",
        "0",
        "(",
        ")",
        "="
    )}

    fun isOperator(s: String): Boolean {
        return s == "/" || s == "*" || s == "-" || s == "+"
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
    ) {
        items(buttons) { symbol ->
            when (symbol) {
                "DEL" -> CalcButton(
                    color = colorResource(id = R.color.btnBgColor),
                    textColor = colorResource(id = R.color.leftOperatorColor),
                    text = symbol
                ) {
                    calculator.delete()
                }

                "C" -> CalcButton(
                    color = colorResource(id = R.color.btnBgColor),
                    textColor = colorResource(id = R.color.leftOperatorColor),
                    text = symbol
                ) {
                    calculator.clear()
                }

                "=" -> CalcButton(
                    color = colorResource(id = R.color.btnBgColor),
                    textColor = colorResource(id = R.color.leftOperatorColor),
                    text = symbol
                ) {
                    calculator.calculate()
                }

                else -> CalcButton(
                    color = colorResource(id = R.color.btnBgColor),
                    textColor = if (isOperator(symbol)) colorResource(id = R.color.operatorColor) else Color.White,
                    text = symbol
                ) {
                    calculator.addSymbol(symbol)
                }
            }
        }
    }
}