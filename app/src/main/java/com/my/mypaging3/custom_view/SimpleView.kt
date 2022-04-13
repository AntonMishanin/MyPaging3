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

        // onTouchEvent()
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
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
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

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        Log.d("EE", "draw")
    }

    //draw the children
    //draw(canvas: Canvas?) invoke this fun
    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        Log.d("EE", "dispatchDraw")

        //If something about your view changes that will affect the size,
        //then you should call requestLayout()
        //It doesn't guaranteed redraw so you need to invoke invalidate()
        //mParent.requestLayout(); - Also invoke parent's requestLayout()
        //It doesn't invoke child's requestLayout()
        requestLayout()

        //The view will be redrawn but the size will not change.
        invalidate()

        //https://stackoverflow.com/questions/45383948/how-does-forcelayout-work-in-android
        //This method does not call requestLayout() or forceLayout() on the parent.
        //Usage: if you don't want to invoke child's requestLayout() directly
        //You can invoke parent's requestLayout() and invoke child's forceLayout()
        /*From table layout
        public void requestLayout() {
            if (mInitialized) {
                int count = getChildCount();
                for (int i = 0; i < count; i++) {
                    getChildAt(i).forceLayout();
                }
            }

            super.requestLayout();
        }*/
        forceLayout()

        //
        parent.requestDisallowInterceptTouchEvent(true)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("EE", "onDraw")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d("EE", "onDetachedFromWindow")
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun onVisibilityAggregated(isVisible: Boolean) {
        super.onVisibilityAggregated(isVisible)
    }
}