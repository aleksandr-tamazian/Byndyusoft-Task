package com.byndyusoft.task.implementation

sealed class Token {
    data class Number(val value: Double) : Token() {
        override fun toString(): String = value.toString()
    }

    sealed class MonoidOperator(val precendence: Int) : Token() {
        abstract fun execute(lhs: Number, rhs: Number): Number

        object Plus : MonoidOperator(0) {
            override fun execute(lhs: Number, rhs: Number) = Number(value = lhs.value.plus(rhs.value))
        }

        object Minus : MonoidOperator(0) {
            override fun execute(lhs: Number, rhs: Number) = Number(value = lhs.value.minus(rhs.value))
        }

        object Multiply : MonoidOperator(5) {
            override fun execute(lhs: Number, rhs: Number) = Number(value = lhs.value * rhs.value)
        }

        object Divide : MonoidOperator(5) {
            override fun execute(lhs: Number, rhs: Number) = Number(value = lhs.value / rhs.value)
        }
    }
}

sealed class InfixToken : Token() {
    sealed class Brackets : Token() {
        object Open : Brackets()

        object Close : Brackets()
    }
}
