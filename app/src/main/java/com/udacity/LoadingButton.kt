package com.udacity

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlin.properties.Delegates

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var widthSize = 0
    private var heightSize = 0

    private var loadingWidthSize = 0f

    private val valueAnimator = ValueAnimator()

    private var buttonState: ButtonState by Delegates.observable<ButtonState>(ButtonState.Completed) { p, old, new ->
        Log.i("LoadingButton", "p $p old: ${old} -> new: ${new}")
        when(new) {
            is ButtonState.Clicked -> {
                buttonState = ButtonState.Loading
            }
            is ButtonState.Loading -> {
                valueAnimator.setDuration(1000)
                valueAnimator.setFloatValues(0f, widthSize.toFloat())
                valueAnimator.addUpdateListener {
                    loadingWidthSize = it.getAnimatedValue() as Float

                    if (loadingWidthSize == widthSize.toFloat()) {
                        buttonState = ButtonState.Completed
                    }

                    invalidate()
                }
                valueAnimator.start()
            }
            is ButtonState.Completed -> {
                loadingWidthSize = 0f
            }
        }
    }


    init {

    }

    override fun performClick(): Boolean {
        super.performClick()
        buttonState = ButtonState.Clicked
        Toast.makeText(context, context.getString(R.string.toast_message),Toast.LENGTH_LONG).show()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.setColor(resources.getColor(R.color.colorPrimary))
        canvas?.drawRect(0f, 0f, widthSize.toFloat(), heightSize.toFloat(), paint)

        paint.setColor(resources.getColor((R.color.colorPrimaryDark)))
        canvas?.drawRect(0f, 0f, loadingWidthSize.toFloat(), heightSize.toFloat(), paint)

        paint.setColor(Color.WHITE)
        paint.setTextSize(50f)


        when(buttonState) {
            is ButtonState.Completed -> {
                canvas?.drawText("Download",
                    (widthSize/2f) - 100,
                    heightSize/2f + 20,
                    paint)
            }
            is ButtonState.Loading -> {
                canvas?.drawText("We are loading", (widthSize/2f) - 150, heightSize/2f + 20, paint)
            }
        }
        // Draw Arc
        paint.setColor(Color.YELLOW)
        val radius = 80f
        val hoffset = 200
        val voffset = 40
        canvas?.drawArc(
            widthSize/2f + hoffset,
            heightSize/2f - voffset,
            widthSize/2f + hoffset + radius,
            heightSize/2f - voffset + radius,
            0f,
            360f - (widthSize - loadingWidthSize) / widthSize * 360f, true, paint)


        Log.i("LoadingButton", "In onDraw() ${widthSize} ${heightSize} ${loadingWidthSize}")

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)
        val h: Int = resolveSizeAndState(
            MeasureSpec.getSize(w),
            heightMeasureSpec,
            0
        )
        widthSize = w
        heightSize = h
        setMeasuredDimension(w, h)
    }

}