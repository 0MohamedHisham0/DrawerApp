package com.m.hisham.painter.Utiltis

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat
import com.m.hisham.painter.R
import kotlin.math.*

//const for saving before runtime
private const val STROKE_WIDTH = 12f

class CanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.white, null)
    private val drawColor = ResourcesCompat.getColor(resources, R.color.black, null)

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private var path = Path()

    private var isRect: Boolean = false
    private var isLine: Boolean = false
    private var isCircle: Boolean = false
    private var isPaint: Boolean = false

    //onTouchEvent Motions X and Y
    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f

    //touch start
    private var currentX = 0f
    private var currentY = 0f

    //touch Move
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    private var StX: Float = 0f
    private var StY: Float = 0f

    //for Painting
    private val paint = Paint().apply {
        color = drawColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = STROKE_WIDTH
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        motionTouchEventX = event!!.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true

    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY

        if (isRect || isLine || isCircle) {
            StX = currentX
            StY = currentY
        }
    }

    private fun touchMove() {
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - currentY)
        if (dx >= touchTolerance || dy >= touchTolerance) {

            path.quadTo(
                currentX,
                currentY,
                (motionTouchEventX + currentX) / 2,
                (motionTouchEventY + currentY) / 2
            )
            currentX = motionTouchEventX
            currentY = motionTouchEventY

            if (isPaint && !isRect && !isLine && !isCircle) {
                extraCanvas.drawPath(path, paint)
            }

        }
        invalidate()

    }

    private fun touchUp() {
        if (isRect) {
            extraCanvas.drawRect(StX, StY, currentX, currentY, paint)
            invalidate()
        }

        if (isLine) {
            drawArrow(paint, extraCanvas, StX, StY, currentX, currentY)
            invalidate()

        }

        if (isCircle) {
            extraCanvas.drawCircle(
                StX,
                StY,
                sqrt(((currentX - StX) * (currentX - StX)) + ((currentY - StY) * (currentY - StY))),
                paint
            )
            invalidate()

        }

        path.reset()

    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (::extraBitmap.isInitialized) extraBitmap.recycle()

        Log.i("TAG", "onSizeChanged: $w")
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)
        //for dodging memory leak
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(extraBitmap, 0f, 0f, null)


    }


    fun paintColor(color: Int) {
        paint.color = color
    }

    fun strokeWidth(strokeWidth: Float) {
        paint.strokeWidth = strokeWidth
    }


    fun drawWithPaint() {
        isLine = false
        isRect = false
        isCircle = false
        isPaint = true
    }

    fun drawRect() {
        isLine = false
        isRect = true
        isCircle = false
    }

    fun drawLine() {
        isRect = false
        isLine = true
        isCircle = false
    }

    fun drawCircle() {
        isRect = false
        isLine = false
        isCircle = true
    }


    private fun drawArrow(
        paint: Paint,
        canvas: Canvas,
        from_x: Float,
        from_y: Float,
        to_x: Float,
        to_y: Float
    ) {
        val angle: Float
        val anglerad: Float
        val radius: Float
        val lineangle: Float

        //values to change for other appearance *CHANGE THESE FOR OTHER SIZE ARROWHEADS*
        radius = 40f + (paint.strokeWidth - (paint.strokeWidth/2))
        angle = 60f + (paint.strokeWidth - (paint.strokeWidth/2))

        //some angle calculations
        anglerad = ((PI * angle / 180.0f).toFloat())
        lineangle = atan2(to_y - from_y, to_x - from_x) as Float

        //tha line
        canvas.drawLine(from_x, from_y, to_x, to_y, paint)

        //tha triangle
        val path = Path()
        path.fillType = Path.FillType.EVEN_ODD
        path.moveTo(to_x, to_y)
        path.lineTo(
            (to_x - radius * cos(lineangle - anglerad / 2.0)).toFloat(),
            (to_y - radius * sin(lineangle - anglerad / 2.0)).toFloat()
        )
        path.lineTo(
            (to_x - radius * cos(lineangle + anglerad / 2.0)).toFloat(),
            (to_y - radius * sin(lineangle + anglerad / 2.0)).toFloat()
        )
        path.close()
        canvas.drawPath(path, paint)
    }
}