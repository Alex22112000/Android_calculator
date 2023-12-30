package com.example.calculator.domain.api

import com.example.calculator.domain.Result

interface ICalculatorHistory {
    fun getHistory(): List<Result>
}