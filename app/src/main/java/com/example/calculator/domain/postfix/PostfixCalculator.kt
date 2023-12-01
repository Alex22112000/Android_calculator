package com.example.calculator.domain.postfix

import com.example.calculator.domain.api.ICalculator
import java.util.Stack

class PostfixCalculator : ICalculator {
    override fun calculate(tokens: List<String>): Double {
        val locals = Stack<Double>()

        for (token in tokens){
            if (token.toDoubleOrNull() != null){
                locals.push(token.toDouble())
            } else if (isOperator(token)){
                if (token == "~"){
                    val last = if (locals.isNotEmpty()) locals.pop() else 0.0
                    locals.push(execute("-", 0.0, last))
                    continue
                }

                val second = if (locals.isNotEmpty()) locals.pop() else 0.0
                val first = if (locals.isNotEmpty()) locals.pop() else 0.0
                locals.push(execute(token, first, second))
            }
        }

        return locals.pop()
    }

    private fun isOperator(token: String): Boolean {
        val operators = listOf("+", "-", "*", "/", "~")
        return operators.contains(token)
    }

    private fun execute(operator: String, first: Double, second: Double): Double{
        return when (operator){
            "+" -> first + second
            "-" -> first - second
            "*" -> first * second
            "/" -> first / second
            else -> 0.0
        }
    }
}