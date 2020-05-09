package com.tongchen.baselib.behavior

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tongchen.baselib.util.LogUtils


/**
 * Created by TongChen on 2017/7/11.
 *
 *
 * Description:
 */

class FabScrollAwareBehavior(context: Context, attrs: AttributeSet) : FloatingActionButton.Behavior() {
    private var mIsAnimatingOut = false

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton,
                                     directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean {
        // Ensure we react to vertical scrolling
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes, 0)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton,
                                target: View, dxConsumed: Int, dyConsumed: Int,
                                dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, 0)
        LogUtils.d("TBeh", dyConsumed.toString() + "---" + child.visibility)
        if (dyConsumed > 0 && !this.mIsAnimatingOut && child.visibility == View.VISIBLE) {
            // User scrolled down and the FAB is currently visible -> hide the FAB
            animateOut(child)
        } else if (dyConsumed < 0 && child.visibility != View.VISIBLE) {
            // User scrolled up and the FAB is currently not visible -> show the FAB
            animateIn(child)
        }
    }

    // Same animation that FloatingActionButton.Behavior uses to hide the FAB when the AppBarLayout exits
    private fun animateOut(button: FloatingActionButton) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            ViewCompat.animate(button).translationY((button.height + getMarginBottom(button)).toFloat()).setInterpolator(INTERPOLATOR).withLayer()
                    .setListener(object : ViewPropertyAnimatorListener {
                        override fun onAnimationStart(view: View) {
                            this@FabScrollAwareBehavior.mIsAnimatingOut = true
                        }

                        override fun onAnimationCancel(view: View) {
                            this@FabScrollAwareBehavior.mIsAnimatingOut = false
                        }

                        override fun onAnimationEnd(view: View) {
                            this@FabScrollAwareBehavior.mIsAnimatingOut = false
                            //  将GONE改为INVISIBLE，原因见  http://blog.csdn.net/sam_zhang1984/article/details/72830074   最后
                            //  view.setVisibility(View.GONE);
                            view.visibility = View.INVISIBLE
                        }
                    }).start()
        }
    }

    // Same animation that FloatingActionButton.Behavior uses to show the FAB when the AppBarLayout enters
    @SuppressLint("RestrictedApi")
    private fun animateIn(button: FloatingActionButton) {
        button.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            ViewCompat.animate(button).translationY(0f)
                    .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
                    .start()
        }
    }

    private fun getMarginBottom(v: View): Int {
        var marginBottom = 0
        val layoutParams = v.layoutParams
        if (layoutParams is ViewGroup.MarginLayoutParams) {
            marginBottom = layoutParams.bottomMargin
        }
        return marginBottom
    }

    companion object {

        private val INTERPOLATOR = FastOutSlowInInterpolator()
    }
}
