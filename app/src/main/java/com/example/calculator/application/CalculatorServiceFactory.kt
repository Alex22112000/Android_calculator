package com.example.calculator.application

import com.example.calculator.domain.postfix.PostfixCalculator
import com.example.calculator.domain.postfix.PostfixExpressionParser

enum class CalculatorServiceType {
    postfix
}

object CalculatorServiceFactory {
    fun create(type: CalculatorServiceType): ICalculatorService {
        return when (type){
            CalculatorServiceType.postfix -> CalculatorService(PostfixExpressionParser(), PostfixCalculator())
            else -> CalculatorService(PostfixExpressionParser(), PostfixCalculator())
        }
    }
}