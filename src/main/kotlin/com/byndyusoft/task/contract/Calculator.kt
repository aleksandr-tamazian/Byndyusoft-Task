package com.byndyusoft.task.contract

interface Calculator {
    fun evaluate(statement: String): Result<Double>
}