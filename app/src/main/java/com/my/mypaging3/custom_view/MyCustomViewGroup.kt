package com.my.mypaging3.custom_view

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup

class MyCustomViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private val child1: View?
        get() = if (childCount > 0) getChildAt(0) else null

    private val child2: View?
        get() = if (childCount > 1) getChildAt(1) else null

    private val animator = ChildAnimation()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)

        child1?.measure(widthMeasureSpec, childHeightSpec)
        child2?.measure(widthMeasureSpec, childHeightSpec)

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        child1?.layout(0, 0, child1?.measuredWidth ?: 0, child1?.measuredHeight ?: 0)
        child2?.layout(
            0,
            child1?.measuredHeight ?: 0,
            child2?.measuredWidth ?: 0,
            (child1?.measuredHeight ?: 0) + (child2?.measuredHeight ?: 0)
        )
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        Log.d("EE", "childCount = $childCount")

        child1?.alpha = 0f
        child2?.alpha = 0f

        animator.setView(child1)
        animator.setEndCallback {

            animator.setView(child2)
            animator.setEndCallback {
                animator.setView()
                animator.cancel()
            }
            animator.start()
        }
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.cancel()
    }
}

class ChildAnimation(private val inputDuration: Long = 1_500) {

    private var view: View? = null

    private var animationEndCallback: (() -> Unit)? = null

    private val animator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
        addUpdateListener { updatedAnimation ->
            view?.alpha = updatedAnimation.animatedValue as Float
        }

        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) = Unit

            override fun onAnimationEnd(animation: Animator?) {
                animationEndCallback?.invoke()
            }

            override fun onAnimationCancel(animation: Animator?) = Unit

            override fun onAnimationRepeat(animation: Animator?) = Unit
        })

        duration = inputDuration
    }

    fun setView(view: View? = null) {
        this.view = view
    }

    fun setEndCallback(animationEndCallback: (() -> Unit)? = null) {
        this.animationEndCallback = animationEndCallback
    }

    fun start() = animator.start()

    fun cancel() = animator.cancel()
}