package com.example.calculator.domain.api

interface IExpressionParser {
    fun parse(expression: String): List<String>
}