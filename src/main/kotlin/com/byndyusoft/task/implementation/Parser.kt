package com.byndyusoft.task.implementation

import java.util.*

object Parser {

    /**
     * Parse sequence of infix tokens to RPN representation.
     *
     * @param statement Original math expression
     * @param tokenParser Polymorphic function for tokens parser
     */
    fun parseInfixTokensToPostfix(statement: String, tokenParser: (String) -> List<Token>): List<Token> {
        val state = Stack<Token>()
        val convertedTokens = mutableListOf<Token>()
        tokenParser(statement).forEach {
            when (it) {
                is Token.MonoidOperator -> {
                    while (state.isNotEmpty() && state.peek() is Token.MonoidOperator) {
                        if (isValidPrecendence(first = it, second = state.peek())) {
                            convertedTokens.add(state.pop())
                            continue
                        }
                        break
                    }

                    state.push(it)
                }
                is InfixToken.Brackets.Open -> {
                    state.push(it)
                }
                is InfixToken.Brackets.Close -> {
                    while (state.isNotEmpty() && state.peek() !is InfixToken.Brackets.Close)
                        convertedTokens.add(state.pop())

                    if (state.isNotEmpty())
                        state.pop()
                }
                is Token.Number -> {
                    convertedTokens.add(it)
                }
            }
        }

        while (state.isNotEmpty())
            convertedTokens.add(state.pop())

        return convertedTokens
    }

    private fun isValidPrecendence(first: Token.MonoidOperator, second: Token): Boolean =
        if (second !is Token.MonoidOperator)
            throw IllegalArgumentException("Invalid tokens: $first. $second")
        else
            first.precendence - second.precendence <= 0
}