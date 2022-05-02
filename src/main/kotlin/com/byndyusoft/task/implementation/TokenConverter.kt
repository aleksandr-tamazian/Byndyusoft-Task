package com.byndyusoft.task.implementation

object TokenConverter {
    fun convertStringToInfixTokens(statement: String): List<Token> =
        statement
            .replace("\\s".toRegex(), "")
            .split("(?<=[\\-+*/=()])|(?=[()\\-+*/=])".toRegex())
            .filter { it != "" }
            .map {
                when (it.trim()) {
                    "+" -> Token.MonoidOperator.Plus
                    "-" -> Token.MonoidOperator.Minus
                    "*" -> Token.MonoidOperator.Multiply
                    "/" -> Token.MonoidOperator.Divide
                    "(" -> InfixToken.Brackets.Open
                    ")" -> InfixToken.Brackets.Close
                    else -> Token.Number(value = it.trim().toDouble())
                }
            }
}