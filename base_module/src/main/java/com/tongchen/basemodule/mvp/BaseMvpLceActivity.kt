package com.tongchen.basemodule.mvp

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.NonNull
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.lce.LceAnimator
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView

/**
 * @author TongChen
 * @date 2019/09/07  14:05
 * <p>
 * Desc: mosby MVP的修改版
 */
abstract class BaseMvpLceActivity<CV : View, M, V : MvpLceView<M>, P : MvpBasePresenter<V>> : BaseMvpActivity<V, P>(), MvpLceView<M> {

    protected lateinit var mLoadingView: View
    protected lateinit var mContentView: CV
    protected lateinit var mErrorView: TextView

    @CallSuper
    override fun onContentChanged() {
        super.onContentChanged()
        mLoadingView = createLoadingView()
        mContentView = createContentView() as CV
        mErrorView = createErrorView()

        mErrorView.setOnClickListener {
            onErrorViewClicked()
        }
    }

    @NonNull
    protected abstract fun createLoadingView(): View

    @NonNull
    protected abstract fun createContentView(): View

    @NonNull
    protected abstract fun createErrorView(): TextView

    /**
     * Called if the error view has been clicked. To disable clicking on the mErrorView use
     * `mErrorView.setClickable(false)`
     */
    protected fun onErrorViewClicked() {
        loadData(false)
    }

    override fun showLoading(pullToRefresh: Boolean) {

        if (!pullToRefresh) {
            animateLoadingViewIn()
        }

        // otherwise the pull to refresh widget will already display a loading animation
    }

    /**
     * Override this method if you want to provide your own animation for showing the loading view
     */
    protected fun animateLoadingViewIn() {
        LceAnimator.showLoading(mLoadingView, mContentView, mErrorView)
    }

    override fun showContent() {
        animateContentViewIn()
    }

    /**
     * Called to animate from loading view to content view
     */
    protected fun animateContentViewIn() {
        LceAnimator.showContent(mLoadingView, mContentView, mErrorView)
    }

    /**
     * Get the error message for a certain Exception that will be shown on [ ][.showError]
     */
    protected abstract fun getErrorMessage(e: Throwable, pullToRefresh: Boolean): String

    /**
     * The default behaviour is to display a toast message as light error (i.e. pull-to-refresh
     * error).
     * Override this method if you want to display the light error in another way (like crouton).
     */
    protected fun showLightError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showError(e: Throwable, pullToRefresh: Boolean) {

        val errorMsg = getErrorMessage(e, pullToRefresh)

        if (pullToRefresh) {
            showLightError(errorMsg)
        } else {
            mErrorView.text = errorMsg
            animateErrorViewIn()
        }
    }

    /**
     * Animates the error view in (instead of displaying content view / loading view)
     */
    protected fun animateErrorViewIn() {
        LceAnimator.showErrorView(mLoadingView, mContentView, mErrorView)
    }


}