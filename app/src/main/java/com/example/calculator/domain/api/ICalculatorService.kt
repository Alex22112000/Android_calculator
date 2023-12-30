package com.example.calculator.domain.api

import com.example.calculator.domain.Result

interface ICalculatorService {
    suspend fun calculate(expression: String): Double?
}