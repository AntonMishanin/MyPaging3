package com.my.mypaging3.custom_view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.my.mypaging3.R

class MyViewHolder : FrameLayout {

    private lateinit var view: View

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        view = View.inflate(context, R.layout.item_user, this)

        view.findViewById<TextView>(R.id.title).text = "SDSDSDSDS"
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