package com.tongchen.gank.biz.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author TongChen
 * @date 2020/11/06  10:31
 * <p>
 * Desc:
 */
@Parcelize
data class Category(
    //  所属的分类，默认All
    var mCategory: String = "All",
    //  显示的名称
    var mTitle: String = "",
    //  请求用的参数
    var mType: String = "",
    //  当前分类在分类List中的下标
    var mIndex: Int = 0,
    //  分类List的大小
    var mCount: Int = 0

) : BaseBean(), Parcelable {

    constructor(builder: Builder) : this() {
        mCategory = builder.category
        mTitle = builder.title
        mType = builder.type
        mIndex = builder.index
        mCount = builder.count
    }

    class Builder {
        var category: String = "All"
        var title: String = ""
        var type: String = ""
        var index: Int = 0
        var count = 9

        fun category(category: String) {
            this.category = category
        }

        fun title(title: String): Builder {
            this.title = title
            return this
        }

        fun type(type: String): Builder {
            this.type = type
            return this
        }

        fun index(index: Int): Builder {
            this.index = index
            return this
        }

        fun count(count: Int): Builder {
            this.count = count
            return this
        }

        fun build(): Category {
            return Category(this)
        }
    }

}


