package com.m.hisham.painter.UI

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.m.hisham.painter.R
import com.m.hisham.painter.Utiltis.*
import kotlinx.android.synthetic.main.activity_main.*

private const val selectorColor = R.color.Selection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initOnClickButtons()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initOnClickButtons() {
        var currentColor = "#000000"

        //Default Paint Selected
        canvas.drawWithPaint()
        changeIconColorFromImageView(paint, R.color.black)

        //Tools
        colors.setOnClickListener {
            if (colors_list.visibility == View.GONE) {
                colors_list.visibility = View.VISIBLE
                slideInRight(colors_list, 500)
            } else {
                slideOutRight(colors_list, 500)
                colors_list.visibility = View.GONE
            }

        }

        paint.setOnClickListener {
            //get Last used Color
            canvas.paintColor(Color.parseColor(currentColor))

            paint.background = resources.getDrawable(selectorColor, theme)
            changeIconColorFromImageView(paint, R.color.black)
            slideInTop(paintCard, 300)



            canvas.drawWithPaint()
            restTool(paint)
        }

        circle.setOnClickListener {

            //get Last used Color
            canvas.paintColor(Color.parseColor(currentColor))

            circle.background = resources.getDrawable(selectorColor, theme)
            changeIconColorFromImageView(circle, R.color.black)
            slideInTop(circleCard, 300)


            canvas.drawCircle()
            restTool(circle)

        }

        line.setOnClickListener {
            //get Last used Color
            canvas.paintColor(Color.parseColor(currentColor))

            line.background = resources.getDrawable(selectorColor, theme)
            changeIconColorFromImageView(line, R.color.black)
            slideInTop(lineCard, 300)


            canvas.drawLine()
            restTool(line)

        }

        rectangle.setOnClickListener {
            //get Last used Color
            canvas.paintColor(Color.parseColor(currentColor))

            rectangle.background = resources.getDrawable(selectorColor, theme)
            changeIconColorFromImageView(rectangle, R.color.black)
            slideInTop(rectangleCard, 300)



            canvas.drawRect()
            restTool(rectangle)

        }

        erase.setOnClickListener {
            erase.background = resources.getDrawable(selectorColor, theme)
            changeIconColorFromImageView(erase, R.color.black)
            slideInTop(eraseCard, 300)
            canvas.drawWithPaint()

            canvas.paintColor(Color.parseColor("#FFFFFF"))
            restTool(erase)

        }

        //Colors
        color1.setOnClickListener {
            restCheckedButton()
            color1.isChecked = true

            currentColor = "#FB0008"

            canvas.paintColor(Color.parseColor(currentColor))
            changeIconColorFromImageView2(colors, Color.parseColor(currentColor))

        }

        color2.setOnClickListener {
            restCheckedButton()
            color2.isChecked = true

            currentColor = "#007F00"

            canvas.paintColor(Color.parseColor(currentColor))
            changeIconColorFromImageView2(colors, Color.parseColor(currentColor))


        }

        color3.setOnClickListener {
            restCheckedButton()
            color3.isChecked = true
            currentColor = "#0078DE"
            canvas.paintColor(Color.parseColor(currentColor))
            changeIconColorFromImageView2(colors, Color.parseColor(currentColor))

        }

        color4.setOnClickListener {
            restCheckedButton()
            color4.isChecked = true

            currentColor = "#353434"
            canvas.paintColor(Color.parseColor(currentColor))
            changeIconColorFromImageView2(colors, Color.parseColor(currentColor))


        }

        //seekBar
        seekBar.progress = 30
        canvas.strokeWidth(seekBar.progress.toFloat())
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                canvas.strokeWidth(seekBar.progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                canvas.strokeWidth(seekBar.progress.toFloat())
            }
        })

    }

    private fun restCheckedButton() {
        color1.isChecked = false
        color2.isChecked = false
        color3.isChecked = false
        color4.isChecked = false
    }

    private fun restTool(view: View) {
        if (view == circle) {
            paint.background = null
            line.background = null
            rectangle.background = null
            erase.background = null

            changeIconColorFromImageView(paint, R.color.DefaultIconColor)
            changeIconColorFromImageView(line, R.color.DefaultIconColor)
            changeIconColorFromImageView(rectangle, R.color.DefaultIconColor)
            changeIconColorFromImageView(erase, R.color.DefaultIconColor)
        }
        if (view == line) {
            paint.background = null
            circle.background = null
            rectangle.background = null
            erase.background = null

            changeIconColorFromImageView(paint, R.color.DefaultIconColor)
            changeIconColorFromImageView(circle, R.color.DefaultIconColor)
            changeIconColorFromImageView(rectangle, R.color.DefaultIconColor)
            changeIconColorFromImageView(erase, R.color.DefaultIconColor)

        }
        if (view == rectangle) {
            paint.background = null
            circle.background = null
            line.background = null
            erase.background = null

            changeIconColorFromImageView(paint, R.color.DefaultIconColor)
            changeIconColorFromImageView(circle, R.color.DefaultIconColor)
            changeIconColorFromImageView(line, R.color.DefaultIconColor)
            changeIconColorFromImageView(erase, R.color.DefaultIconColor)
        }
        if (view == erase) {
            paint.background = null
            circle.background = null
            line.background = null
            rectangle.background = null

            changeIconColorFromImageView(paint, R.color.DefaultIconColor)
            changeIconColorFromImageView(circle, R.color.DefaultIconColor)
            changeIconColorFromImageView(line, R.color.DefaultIconColor)
            changeIconColorFromImageView(rectangle, R.color.DefaultIconColor)
        }

        if (view == paint) {
            erase.background = null
            circle.background = null
            line.background = null
            rectangle.background = null

            changeIconColorFromImageView(circle, R.color.DefaultIconColor)
            changeIconColorFromImageView(line, R.color.DefaultIconColor)
            changeIconColorFromImageView(rectangle, R.color.DefaultIconColor)
            changeIconColorFromImageView(erase, R.color.DefaultIconColor)
        }
    }

    private fun changeIconColorFromImageView(image: ImageView, color: Int) {
        DrawableCompat.setTint(
            DrawableCompat.wrap(image.drawable),
            ContextCompat.getColor(applicationContext, color)
        );
    }
    private fun changeIconColorFromImageView2(image: ImageView, color: Int) {
        DrawableCompat.setTint(
            DrawableCompat.wrap(image.drawable),
            color
        );
    }
}
