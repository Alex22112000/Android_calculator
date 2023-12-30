package com.example.calculator.infrastructure.state

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.CalculatorHistory
import com.example.calculator.domain.CalculatorServiceFactory
import com.example.calculator.domain.CalculatorServiceType
import com.example.calculator.domain.Result
import com.example.calculator.domain.api.ICalculatorHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CalculatorState (
    val input: String = "",
    val output: String = "",
    val isOnline: Boolean = false,
    val isLoading: Boolean = false,
    val history: List<Result> = emptyList()
)

object CalculatorViewModel : ViewModel() {
    private val _calculatorState = MutableStateFlow(CalculatorState())
    val calculatorState: StateFlow<CalculatorState> = _calculatorState.asStateFlow()
    private val calculatorHistory: ICalculatorHistory = CalculatorHistory()

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
        val calculatorService = CalculatorServiceFactory.create(if(calculatorState.value.isOnline) CalculatorServiceType.online else CalculatorServiceType.offline)

        CoroutineScope(Dispatchers.IO).launch {
            _calculatorState.update {
                    state -> state.copy(isLoading = true)
            }
            val output: Double? = calculatorService.calculate(calculatorState.value.input)
            _calculatorState.update {
                state -> state.copy(output = output?.toString() ?: "Error")
            }
            _calculatorState.update {
                    state -> state.copy(isLoading = false)
            }
        }


    }

    fun addSymbol(symbol: String) {
        _calculatorState.update {
            state -> state.copy(input = state.input + symbol)
        }
    }

    fun changeMod(){
        _calculatorState.update {
                state -> state.copy(isOnline = !state.isOnline)
        }
    }

    fun getHistory(){
        _calculatorState.update {
            state -> state.copy(history = calculatorHistory.getHistory())
        }
    }
}