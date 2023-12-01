package com.example.calculator.domain.postfix

import com.example.calculator.domain.api.IExpressionParser
import java.util.Stack

class PostfixExpressionParser : IExpressionParser {

    private var position: Int = 0

    private val operationPriority: Map<String, Int> = mapOf(
        "(" to 0,
        "+" to 1,
        "-" to 1,
        "*" to 2,
        "/" to 2,
        "~" to 3
    )

    override fun parse(expression: String): List<String> {
        val tokens: MutableList<String> = mutableListOf()
        val stack: Stack<String> = Stack()

        while (position < expression.length){
            val c: Char = expression[position]

            if (c.isDigit() || c == '.'){
                tokens.add(getStringNumber(expression))
            } else if (c == '('){
                stack.push(c.toString())
            } else if (c == ')'){
                while (stack.isNotEmpty() && stack.peek() != "(") {
                    tokens.add(stack.pop())
                }
                stack.pop()
            } else if (operationPriority.containsKey(c.toString())) {
                var operation = c.toString()

                if (operation == "-" &&
                    (position == 0 || position > 1 && operationPriority.containsKey(expression[position - 1].toString()))){
                    operation = "~"
                }

                while (stack.isNotEmpty() &&
                operationPriority[stack.peek()]!! >= operationPriority[operation]!!){
                    tokens.add(stack.pop())
                }

                stack.push(operation)
            }
            position++
        }

        while (stack.isNotEmpty()){
            tokens.add(stack.pop())
        }

        position = 0
        return tokens
    }

    private fun getStringNumber(expression: String): String {
        var strNumber = ""

        while (position < expression.length){
            val num: Char = expression[position]
            if (num.isDigit() || num == '.'){
                strNumber += num
            } else {
                this.position--
                break
            }
            position++
        }

        return strNumber
    }
}