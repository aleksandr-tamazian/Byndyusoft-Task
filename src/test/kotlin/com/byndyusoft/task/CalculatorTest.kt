package com.byndyusoft.task

import com.byndyusoft.task.contract.Calculator
import com.byndyusoft.task.implementation.CalculatorImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CalculatorTest {

    private val calculatorContract: Calculator = CalculatorImpl()

    @Test
    fun `1 _ should evaluate expression correctly`() {
        assertEquals(40.0, calculatorContract.evaluate(" 46 - 2 * 3").getOrNull())
    }

    @Test
    fun `2 _ should evaluate expression correctly`() {
        assertEquals(15.5, calculatorContract.evaluate(" 10 +    5.5").getOrNull())
    }

    @Test
    fun `3 _ should evaluate expression correctly`() {
        assertEquals(33.0, calculatorContract.evaluate("(12.0 -   2   ) * 3.3").getOrNull())
    }

    @Test
    fun `4 _ should evaluate expression correctly`() {
        assertEquals(Double.POSITIVE_INFINITY, calculatorContract.evaluate("5 / 0").getOrNull())
    }

    @Test
    fun `5 _ should evaluate expression correctly`() {
        assertEquals(-3.0, calculatorContract.evaluate("2 - 5").getOrNull())
    }

    @Test
    fun `6 _ should evaluate expression correctly`() {
        assertEquals(7.0, calculatorContract.evaluate("( 5 ) + 2").getOrNull())
    }

    @Test
    fun `1 _ should not evaluate expression correctly`() {
        assertEquals(null, calculatorContract.evaluate("2 - ").getOrNull())
    }

    @Test
    fun `2 _ should not evaluate expression correctly`() {
        assertEquals(null, calculatorContract.evaluate("5 - 2 + + 2").getOrNull())
    }

    @Test
    fun `3 _ should not evaluate expression correctly`() {
        assertEquals(null, calculatorContract.evaluate("* - / + 1").getOrNull())
    }
}