package com.example.calculator.application

interface ICalculatorService {
    suspend fun calculate(expression: String): Double?
}