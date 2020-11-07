package com.tongchen.gank.biz

import android.annotation.SuppressLint
import com.tongchen.baselib.util.LogUtils
import com.tongchen.basemodule.base.BaseMvpContract
import com.tongchen.gank.biz.entity.GankData
import com.tongchen.gank.biz.entity.GankResult
import com.tongchen.gank.biz.ui.fragment.CategoryFragment
import com.tongchen.gank.net.GankApiHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface CategoryContract : BaseMvpContract {

    class Model : BaseMvpContract.MvpModel {

        constructor(apiHelper: GankApiHelper) : super(apiHelper) {

        }

        fun getGankDataByPage(category: String?, size: Int, page: Int): Observable<GankData<MutableList<GankResult>>> {
            return (mApiHelper as GankApiHelper).getGankDataByPage(category, size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }


    interface View : BaseMvpContract.MvpView {

        fun refreshSuccess(result: MutableList<GankResult>?)

        fun refreshFail(msg: String)

        fun loadMoreSuccess(result: MutableList<GankResult>?)

        fun loadMoreFail(msg: String)
    }


    class Presenter : BaseMvpContract.MvpPresenter<Model, View> {

        companion object {
            private const val MODE_REFRESH = 0
            private const val MODE_MORE = 1
        }

        //  mode 请求的方式：0 refresh; 1 loadMore
        private var mLoadMode = 0

        constructor(model: Model, view: View) : super(model, view) {

        }

        @SuppressLint("CheckResult")
        fun refreshData(category: String?, size: Int) {
            mLoadMode = MODE_REFRESH
            (mModel as Model).getGankDataByPage(category, size, 1)
                .subscribe({
                    if (it.mError) {
                        requestFailed("error = ${it.mError}")

                    } else {
                        requestSucceed(it)
                    }
                }, { requestFailed(it.toString()) })
        }

        @SuppressLint("CheckResult")
        fun loadMoreData(category: String?, size: Int, page: Int) {
            mLoadMode = MODE_MORE
            (mModel as Model).getGankDataByPage(category, size, page)
                .subscribe({
                    if (it.mError) {
                        requestFailed("error = ${it.mError}")

                    } else {
                        requestSucceed(it)
                    }
                }, { requestFailed(it.toString()) })
        }


        private fun requestSucceed(result: GankData<MutableList<GankResult>>?) {
            LogUtils.d("CategoryContract", "requestSucceed:${result.toString()}")
            when (mLoadMode) {
                MODE_REFRESH -> {
                    (mView as View).refreshSuccess(result?.mResult)
                }

                MODE_MORE -> {
                    (mView as View).loadMoreSuccess(result?.mResult)
                }
            }
        }

        private fun requestFailed(errorMsg: String) {
            LogUtils.d("CategoryContract", "requestFailed:$errorMsg")
            when (mLoadMode) {
                MODE_REFRESH -> {
                    (mView as View).refreshFail(errorMsg)
                }

                MODE_MORE -> {
                    (mView as View).loadMoreFail(errorMsg)
                }
            }
        }
    }

}