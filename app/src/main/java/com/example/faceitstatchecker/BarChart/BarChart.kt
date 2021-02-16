package com.example.faceitstatchecker.BarChart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.ContextCompat
import com.example.faceitstatchecker.R
import kotlin.math.ceil
import kotlin.math.floor

/**
 * Created by George Hart on 13/02/2021;
 */
class BarChart @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0): Chart(context, attributeSet, defStyleAttr) {
    private val barCalculator = BarCalculator()
    private val rect = RectF()
    private val painter = Paint()
    private val textBoundRect = Rect()
    private var orientation = Orientation.HORIZONTAL
    private var graphData: List<GraphData>? = mutableListOf()

    fun setGraphData(data: List<GraphData>){
        graphData = data
        this.invalidate()
        this.requestLayout()
    }
    fun setOrientation(orientation: Orientation){
        this.orientation = orientation
        this.invalidate()
        this.requestLayout()
    }

    class DummyData(value: Float, label: String = "") : GraphData(value, label)
    init {
        val styledAttrs = context.obtainStyledAttributes(attributeSet, R.styleable.BarChart)
        orientation = Orientation.fromValue(styledAttrs.getInt(R.styleable.BarChart_bar_orientation, 1))
        painter.color = context.resources.getColor(R.color.faceit_orange, null)
        setCardBackgroundColor(resources.getColor(R.color.faceit_grey, null))
        styledAttrs.recycle()
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val availableWidth = if(MeasureSpec.getSize(widthMeasureSpec) == 0) {
            (context.resources.displayMetrics.widthPixels * 0.8).toInt()
        } else MeasureSpec.getSize(widthMeasureSpec)
        val height = availableWidth * 0.8
        val updatedWidthMeasureSpec = MeasureSpec.makeMeasureSpec(availableWidth, MeasureSpec.EXACTLY)
        val updatedHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height.toInt(), MeasureSpec.EXACTLY)
        radius = (height * 0.05f).toFloat()
        super.onMeasure(updatedWidthMeasureSpec, updatedHeightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(orientation == Orientation.VERTICAL) {
            drawVertical(canvas)
        } else {
            drawHorizontal(canvas)
        }
    }

    private fun drawVertical(canvas: Canvas?) {
        val drawGraphData = graphData
        if(drawGraphData != null){
            var tempMax = 0f
                for(item in drawGraphData){
                   if(item.value > tempMax) tempMax = item.value
                }
            val chartMaxValue = tempMax
            val barWidth = barCalculator.calculateBarWidth(width, drawGraphData.size)
            for(index in drawGraphData.indices){
                val item = drawGraphData[index]
                rect.left =  barWidth + ((barWidth * 2) * (index))
                rect.right = rect.left + barWidth
                rect.bottom = height * 0.9f
                rect.top = rect.bottom - barCalculator.calculateBarHeight(chartMaxValue, item.value, floor(height * 0.8f).toInt())
                canvas?.drawRoundRect(rect, barWidth, barWidth, painter)
            }
        } else {
            TODO("Draw placeholder")
        }
    }
    private fun drawHorizontal(canvas: Canvas?) {
        val drawGraphData = graphData
        if(drawGraphData != null){
            var tempMax = 0f
            for(item in drawGraphData){
                if(item.value > tempMax) tempMax = item.value
            }
            val chartMaxValue = tempMax
            val barWidth = barCalculator.calculateBarWidth(height, drawGraphData.size)
            for(index in drawGraphData.indices){
                val item = drawGraphData[index]
                rect.top =  barWidth + ((barWidth * 2) * (index))
                rect.bottom = rect.top + barWidth
                rect.left = width * 0.1f
                rect.right = rect.left + barCalculator.calculateBarHeight(chartMaxValue, item.value, floor(width * 0.8f).toInt())
                canvas?.drawRoundRect(rect, barWidth, barWidth, painter)
            }
        } else {
            TODO("Draw placeholder")
        }
    }
    enum class Orientation(value: Int){
        HORIZONTAL(0), VERTICAL(1);
        companion object {
            fun fromValue(value: Int): Orientation{
                return if(value == 0){
                    HORIZONTAL
                } else {
                    VERTICAL
                }
            }
        }
    }
}