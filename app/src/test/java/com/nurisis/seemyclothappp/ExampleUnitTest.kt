package com.nurisis.seemyclothappp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val calculator = Calculator<Number>()

    @Test
    fun addTest() {
        assertEquals(5.toDouble(), calculator.add(-2,7),0.0)
    }

    @Test
    fun remainderTest() {
        assertEquals(1, calculator.remainder(5,3))
    }
    @Test
    fun remainderTestWithZero() {
        assertEquals(0, calculator.remainder(3,0))
    }
}
