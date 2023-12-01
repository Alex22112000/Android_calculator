package com.example.calculator.state

import androidx.lifecycle.ViewModel
import com.example.calculator.application.CalculatorServiceFactory
import com.example.calculator.application.CalculatorServiceType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CalculatorState (
    val input: String = "",
    val output: String = ""
)

class CalculatorViewModel : ViewModel() {
    private val calculatorService = CalculatorServiceFactory.create(CalculatorServiceType.postfix)
    private val _calculatorState = MutableStateFlow(CalculatorState())
    val calculatorState: StateFlow<CalculatorState> = _calculatorState.asStateFlow()

    fun delete(){
        if (calculatorState.value.input.isEmpty()) return
        _calculatorState.update {
            state ->  state.copy(input = state.input.substring(0, state.input.length - 1), output ="")
        }
    }

    fun clear(){
        _calculatorState.update {
            state -> state.copy(input = "", output = "")
        }
    }

    fun calculate(){
        if (calculatorState.value.input.isEmpty()) return
        _calculatorState.update {
            state -> state.copy(output = calculatorService.calculate(state.input).toString())
        }
    }

    fun addSymbol(symbol: String) {
        _calculatorState.update {
            state -> state.copy(input = state.input + symbol)
        }
    }
}