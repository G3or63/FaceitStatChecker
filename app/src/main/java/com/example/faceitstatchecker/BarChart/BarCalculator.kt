package com.example.faceitstatchecker.BarChart

import android.util.Log
import kotlin.math.ceil

/**
 * Created by George Hart on 14/02/2021;
 */
class BarCalculator {
    fun calculateBarWidth(availableSpace: Int, quantity: Int): Float {
        //* 2 to account for bar spacing
        //+ 2 for padding start & end
        val qty = quantity * 2 + 2
        return ceil((availableSpace / qty.toDouble())).toFloat()
    }

    fun calculateBarHeight(chartMaxValue: Float, value: Float, usableHeight: Int): Float {
        val valueDecimalPercent = if(value >= chartMaxValue) 1.0f else value / chartMaxValue
        return usableHeight * valueDecimalPercent
    }
}