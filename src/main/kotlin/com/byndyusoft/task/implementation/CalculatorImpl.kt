package com.byndyusoft.task.implementation

import com.byndyusoft.task.contract.Calculator

class CalculatorImpl : Calculator {
    override fun evaluate(statement: String): Result<Double> = try {
        Parser
            .parseInfixTokensToPostfix(statement, TokenConverter::convertStringToInfixTokens)
            .fold(emptyList<Token.Number>()) { prev, curr ->
                when (curr) {
                    is Token.Number -> prev + listOf(curr)
                    is Token.MonoidOperator -> {
                        val state = prev.toMutableList()
                        val rhs = state.removeAt(state.size - 1)
                        val lhs = state.removeAt(state.size - 1)
                        state + listOf(curr.execute(lhs = lhs, rhs = rhs))
                    }
                    else -> prev
                }
            }
            .first()
            .let { Result.success(it.value) }
    } catch (e: Exception) {
        Result.failure(e)
    }
}