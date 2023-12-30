package com.example.calculator.domain

import com.example.calculator.domain.api.ICalculator
import com.example.calculator.domain.api.ICalculatorService
import com.example.calculator.domain.api.IExpressionParser
import com.example.calculator.infrastructure.data.SharedHistory

class CalculatorService(
    private val parser: IExpressionParser,
    private val calculator: ICalculator
) : ICalculatorService {

    private val history = SharedHistory

    override suspend fun calculate(expression: String): Double? {
        var result: Double? = null
        try {
            result = calculator.calculate(parser.parse(expression))
            history.add(Result(expression, result.toString()))
        } catch (e: Exception){
            result = null
        }
        return result
    }
}