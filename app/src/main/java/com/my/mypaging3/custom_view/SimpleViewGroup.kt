package com.my.mypaging3.custom_view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.LinearLayout

class SimpleViewGroup : LinearLayout {

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
        requestLayout()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }
}