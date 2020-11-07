package com.tongchen.gank.biz.ui.adapter

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tongchen.baselib.util.ScreenUtils
import com.tongchen.gank.biz.entity.GankResult
import com.tongchen.basewidget.adapter.BaseRecyclerAdapter
import com.tongchen.gank.R
import com.tongchen.gank.biz.ui.fragment.CategoryFragment
import com.tongchen.gank.net.GankApi

/**
 * @author TongChen
 * @date 2020/11/06  10:15
 * <p>
 * Desc:
 */
class CategoryAdapter : BaseRecyclerAdapter<GankResult> {

    private var mSpanCount: Int

    constructor(context: Context, spanCount: Int, list: MutableList<GankResult>) : super(context, list) {
        mSpanCount = spanCount

        bindLayoutIds()
    }

    fun bindLayoutIds() {
        mLayouts.put(VIEW_TYPE_HEADER, null)
        if (mSpanCount == CategoryFragment.SINGLE_SPAN_COUNT) {
            mLayouts.put(VIEW_TYPE_BODY, R.layout.module_gank_recycle_item_text)

        } else {
            mLayouts.put(VIEW_TYPE_BODY, R.layout.module_gank_recycle_item_img)
        }
        mLayouts.put(VIEW_TYPE_FOOTER, null)
    }

    override fun bindViewHolder(holder: BaseViewHolder, position: Int, itemData: GankResult) {
        when (mSpanCount) {
            CategoryFragment.SINGLE_SPAN_COUNT -> {
                holder.setText(R.id.tv_desc, itemData.desc ?: "")
                holder.setText(R.id.tv_date, itemData.publishedAt ?: "")
                if (itemData.images != null && itemData.images!!.isNotEmpty()) {
                    holder.getView<ImageView>(R.id.iv_preview).visibility = View.VISIBLE
                    Glide.with(mContext).load(itemData.images!![0]).into(holder.getView(R.id.iv_preview))
                } else {
                    holder.getView<ImageView>(R.id.iv_preview).visibility = View.GONE
                }
            }

            CategoryFragment.MULTIPLE_SPAN_COUNT -> {
                val height = (ScreenUtils.getScreenHeight(mContext) / 3).toInt()
                holder.getView<ImageView>(R.id.iv_preview).layoutParams.height = height

                if (itemData.url != null && !TextUtils.isEmpty(itemData.url)) {
                    holder.getView<ImageView>(R.id.iv_preview).visibility = View.VISIBLE
                    Glide.with(mContext).load(itemData.url + GankApi.IMAGE_WIDTH_SUFFIX + ScreenUtils.getScreenWidth(mContext) / 2)
                        .into(holder.getView(R.id.iv_preview))
                } else {
                    holder.getView<ImageView>(R.id.iv_preview).visibility = View.GONE
                }
            }
        }
    }

}