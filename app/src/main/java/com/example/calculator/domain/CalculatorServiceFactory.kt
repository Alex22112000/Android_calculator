package com.example.calculator.domain

import com.example.calculator.domain.api.ICalculatorService
import com.example.calculator.domain.postfix.PostfixCalculator
import com.example.calculator.domain.postfix.PostfixExpressionParser
import com.example.calculator.infrastructure.data.CalculatorOnline

enum class CalculatorServiceType {
    online,
    offline
}

object CalculatorServiceFactory {
    fun create(type: CalculatorServiceType): ICalculatorService {
        return when (type){
            CalculatorServiceType.offline -> CalculatorService(PostfixExpressionParser(), PostfixCalculator())
            CalculatorServiceType.online -> CalculatorOnline()
            else -> CalculatorService(PostfixExpressionParser(), PostfixCalculator())
        }
    }
}