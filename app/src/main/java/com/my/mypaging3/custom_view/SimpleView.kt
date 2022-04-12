package com.my.mypaging3.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText

/*class SimpleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {*/

class SimpleView : AppCompatEditText {

    constructor(context: Context) : super(context)

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    init {
        Log.d("EE", "init")
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    setBackgroundColor(Color.RED)
                } else {
                    setBackgroundColor(Color.GRAY)
                }
            }
        })
    }

    override fun onSaveInstanceState(): Parcelable? {
        Log.d("EE", "onSaveInstanceState")
        return super.onSaveInstanceState()
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
        Log.d("EE", "onRestoreInstanceState")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d("EE", "onAttachedToWindow")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(400, 599)
        Log.d("EE", "onMeasure")
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
        Log.d("EE", "layout")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d("EE", "onLayout")
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        Log.d("EE", "dispatchDraw")

        //If something about your view changes that will affect the size,
        //then you should call requestLayout()
        //It doesn't guaranteed redraw
        requestLayout()

        //The view will be redrawn but the size will not change.
        invalidate()

        forceLayout()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("EE", "onDraw")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d("EE", "onDetachedFromWindow")
    }
}