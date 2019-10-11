package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random


class ChartPieView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var lastRandomValue: ArrayList<Int> = ArrayList()
    private var currentRandomValue: Int = -1
    private var arcRect: RectF = RectF()
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var chartData: List<Float> = listOf()
    private var scaledData: MutableList<Float> = mutableListOf()
    private val colors = intArrayOf(Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW, Color.CYAN)
    private var colorRandmizer = Random(colors.size)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (scaledData.isNotEmpty()) {
            arcRect.top = paddingTop.toFloat()
            arcRect.left = paddingLeft.toFloat()
            arcRect.right = width.toFloat()
            arcRect.bottom = width.toFloat()
            paint.strokeWidth = 100f

            var startPoint = 0f
            canvas?.drawArc(arcRect, startPoint, 15f, true, paint)
            arcRect.right = arcRect.right - 10
            canvas?.drawArc(arcRect, 15f, 15f, true, paint)
            arcRect.bottom = arcRect.bottom - 10
            canvas?.drawArc(arcRect, 30f, 15f, true, paint)
            arcRect.right = arcRect.right + 10

            canvas?.drawArc(arcRect, 45f, 15f, true, paint)


            scaledData.forEach {
                paint.color = getRandomColor()
                canvas?.drawArc(arcRect, startPoint, it, true, paint)
                startPoint += it
                arcRect.top += paddingTop
                arcRect.left += paddingLeft
                arcRect.right += paddingRight
                arcRect.bottom += paddingBottom
            }
        }
    }

    fun setData(data: List<Float>) {
        chartData = data
        scaledData = scaleData()
        invalidate()
    }

    private fun scaleData(): MutableList<Float> {
        val scaledValues = mutableListOf<Float>()
        val total = getTotalData()
        for (i in chartData.indices) {
            scaledValues.add(chartData[i] / total * 360)
        }
        return scaledValues
    }

    private fun getTotalData(): Float {
        var total = 0f
        for (i in chartData)
            total += i
        return total
    }

    private fun getRandomColor(): Int {
        currentRandomValue = colorRandmizer.nextInt(0, colors.size)
        return if (!lastRandomValue.contains(currentRandomValue)) {
            lastRandomValue.add(currentRandomValue)
            colors[currentRandomValue]
        } else getRandomColor()
    }
}