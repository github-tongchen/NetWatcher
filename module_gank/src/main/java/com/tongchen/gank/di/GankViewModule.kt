package com.tongchen.gank.di

import androidx.fragment.app.Fragment
import com.tongchen.basemodule.base.BaseApiHelper
import com.tongchen.basemodule.di.ViewScope
import com.tongchen.gank.biz.CategoryContract
import com.tongchen.gank.biz.ui.fragment.CategoryFragment
import com.tongchen.gank.net.GankApiHelper
import dagger.Module
import dagger.Provides

/**
 * @author TongChen
 * @date 2020/11/07  12:00
 * <p>
 * Desc:
 */
@Module
class GankViewModule {

    @ViewScope
    @Provides
    fun provideCategoryPresenter(model: CategoryContract.Model, view: Fragment): CategoryContract.Presenter {
        return CategoryContract.Presenter(model, view as CategoryFragment)
    }

    @ViewScope
    @Provides
    fun provideCategoryModel(apiHelper: BaseApiHelper): CategoryContract.Model {
        return CategoryContract.Model(apiHelper as GankApiHelper)
    }
}