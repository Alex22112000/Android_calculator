package com.example.calculator.domain.api

interface ICalculator {
    fun calculate(tokens: List<String>): Double
}