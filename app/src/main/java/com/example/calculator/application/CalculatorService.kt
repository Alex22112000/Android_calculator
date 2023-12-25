package com.example.calculator.application

import com.example.calculator.domain.api.ICalculator
import com.example.calculator.application.ICalculatorService
import com.example.calculator.domain.api.IExpressionParser

class CalculatorService(
    private val parser: IExpressionParser,
    private val calculator: ICalculator
) : ICalculatorService {

    override suspend fun calculate(expression: String): Double? {
        return try {
            calculator.calculate(parser.parse(expression))
        } catch (e: Exception){
            null
        }
    }

}