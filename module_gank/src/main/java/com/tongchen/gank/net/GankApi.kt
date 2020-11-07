package com.tongchen.gank.net

/**
 * @author TongChen
 * @date 2019/10/29  14:54
 * <p>
 * Desc: 干货集中营 API
 */
interface GankApi {

    companion object {
        const val DOMAIN_NAME_GANK = "domain_name_gank"
        const val DOMAIN_GANK = "https://gank.io/api/"


        /**
         * 加载图片时添加此后缀，并在后面添加宽度来选择图片的大小
         */
        const val IMAGE_WIDTH_SUFFIX = "?imageView2/0/w/"

        /**
         * 加载图片时添加此后缀，并在后面添加高度来选择图片的大小
         */
        const val IMAGE_HEIGHT_SUFFIX = "?imageView2/0/h/"
    }

}
