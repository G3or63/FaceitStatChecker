package com.example.faceitstatchecker.BarChart

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import java.lang.Math.ceil

/**
 * Created by George Hart on 14/02/2021;
 */
class BarCalculatorTest{
    lateinit var barCalculator: BarCalculator
    @Before
    fun setUp(){
        barCalculator = BarCalculator()
    }
    @Test
    fun barWidthReturnedAsHalfAvailableBarSpace(){
        val result = barCalculator.calculateBarWidth(100, 10)
        assertTrue("expected 5 - got $result", result == 5f)
    }
    @Test
    fun barWidthRoundsUp(){
        val result = barCalculator.calculateBarWidth(105,10)
        val expected = kotlin.math.ceil(105 / 22f)
        assertTrue("expected $expected - got $result", result == expected)
    }

    @Test
    fun barHeightSimplePercent(){
        val maxHeight = 100f
        val value = 60f
        val availableHeight = 100
        val result = barCalculator.calculateBarHeight(maxHeight, value, availableHeight)
        assertEquals("expected barHeight 60, got $result", barCalculator.calculateBarHeight(maxHeight, value, availableHeight), 60f, 1f)
    }
    @Test
    fun barHeightComplexPercent_withinOne(){
        val maxHeight = 908.33f
        val value = 136.25f
        val availableHeight = 100
        val expectedResult = 15f
        val result = barCalculator.calculateBarHeight(maxHeight, value, availableHeight)
        assertEquals("expected barHeight $expectedResult, got $result", barCalculator.calculateBarHeight(maxHeight, value, availableHeight), expectedResult, 1f)
    }

    @Test
    fun heightScalesWithAvailability(){
        val maxHeight = 908.33f
        val value = 136.25f
        val availableHeight = 1000
        val expectedResult = 150f
        val result = barCalculator.calculateBarHeight(maxHeight, value, availableHeight)
        assertEquals("expected barHeight $expectedResult, got $result", barCalculator.calculateBarHeight(maxHeight, value, availableHeight), expectedResult, 1f)
    }
}