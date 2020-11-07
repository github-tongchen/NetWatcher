package com.tongchen.basewidget.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.tongchen.baselib.util.LogUtils
import com.tongchen.baselib.util.ToastUtils


/**
 * @author TongChen
 * @date 2019/11/20  16:51
 * <p>
 * Desc:
 */
abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder> {

    protected val VIEW_TYPE_HEADER = 0x00000001
    protected val VIEW_TYPE_FOOTER = 0x00000002
    protected val VIEW_TYPE_BODY = 0x00000000

    protected var mContext: Context
    protected var mData: MutableList<T>
    protected var mInflater: LayoutInflater
    protected var mLayouts: SparseArray<Int>
    private var mItemClickListener: OnItemClickListener? = null
    private var mItemLongClickListener: OnItemLongClickListener? = null

    constructor(context: Context, list: MutableList<T>) {
        mContext = context
        mData = list
        mInflater = LayoutInflater.from(mContext)
        mLayouts = SparseArray()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        LogUtils.d("BaseRecyclerAdapter", "onCreateViewHolder---$viewType")

        val holder = BaseViewHolder(mContext, mInflater.inflate(mLayouts.get(viewType), parent, false))
        holder.itemView.setOnClickListener { mItemClickListener?.onItemClick(holder.itemView, holder.layoutPosition) }
        holder.itemView.setOnLongClickListener {
            mItemLongClickListener?.onItemLongClick(holder.itemView, holder.layoutPosition)
            true
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bindViewHolder(holder, position, mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_BODY
    }

    abstract fun bindViewHolder(holder: BaseViewHolder, position: Int, itemData: T)

    fun setData(list: MutableList<T>) {
        mData.clear()
        mData.addAll(list)
        notifyDataSetChanged()
    }

    fun insertData(itemData: T) {
        val insertPosition = mData.size
        mData.add(itemData)
        notifyItemInserted(insertPosition)
    }

    fun insertData(position: Int, itemData: T) {
        mData.add(position, itemData)
        notifyItemInserted(position)
    }

    fun insertData(list: MutableList<T>) {
        val insertPosition = mData.size
        mData.addAll(list)
        notifyItemRangeInserted(insertPosition, list.size)
    }

    fun insertData(position: Int, list: MutableList<T>) {
        mData.addAll(position, list)
        notifyItemRangeInserted(position, list.size)
    }

    fun deleteData(position: Int) {
        if (position < mData.size) {
            mData.removeAt(position)
            notifyItemRemoved(position)
            //  防止删除错乱 IndexOutOfIndexException等问题
            notifyItemRangeChanged(position, mData.size - position)
        } else {
            ToastUtils.showShort(mContext.applicationContext, "移除失败，position超出数组长度")
        }
    }

    fun deleteData(itemData: T) {
        val deletePosition = mData.indexOf(itemData)
        if (deletePosition != -1) {
            mData.remove(itemData)
            notifyItemRemoved(deletePosition)
            //  防止删除错乱 IndexOutOfIndexException等问题
            notifyItemRangeChanged(deletePosition, mData.size - deletePosition)
        } else {
            ToastUtils.showShort(mContext.applicationContext, "移除失败，不存在符合条件的item")
        }
    }

    fun deleteData(list: MutableList<T>) {
        mData.removeAll(list)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(itemView: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(itemView: View, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mItemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        mItemLongClickListener = listener
    }


    class BaseViewHolder : RecyclerView.ViewHolder {

        private var mContext: Context
        private var mViews: SparseArray<View> = SparseArray()

        constructor(context: Context, itemView: View) : super(itemView) {
            mContext = context
        }

        fun <T : View> getView(@IdRes viewId: Int): T {
            var view: View? = mViews.get(viewId)
            if (view == null) {
                view = itemView.findViewById(viewId)
                mViews.put(viewId, view)
            }
            return view as T
        }

        fun setText(@IdRes viewId: Int, value: CharSequence): BaseViewHolder {
            val view = getView<TextView>(viewId)
            view.text = value
            return this
        }

        fun setText(@IdRes viewId: Int, @StringRes strId: Int): BaseViewHolder {
            val view = getView<TextView>(viewId)
            view.setText(strId)
            return this
        }

        fun setImageResource(@IdRes viewId: Int, @DrawableRes imageResId: Int): BaseViewHolder {
            val view = getView<ImageView>(viewId)
            view.setImageResource(imageResId)
            return this
        }

        fun setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int): BaseViewHolder {
            val view = getView<View>(viewId)
            view.setBackgroundColor(color)
            return this
        }

        fun setBackgroundRes(@IdRes viewId: Int, @DrawableRes backgroundRes: Int): BaseViewHolder {
            val view = getView<View>(viewId)
            view.setBackgroundResource(backgroundRes)
            return this
        }

        fun setTextColor(@IdRes viewId: Int, @ColorInt textColor: Int): BaseViewHolder {
            val view = getView<TextView>(viewId)
            view.setTextColor(textColor)
            return this
        }

        fun setImageDrawable(@IdRes viewId: Int, drawable: Drawable): BaseViewHolder {
            val view = getView<ImageView>(viewId)
            view.setImageDrawable(drawable)
            return this
        }

        fun setImageBitmap(@IdRes viewId: Int, bitmap: Bitmap): BaseViewHolder {
            val view = getView<ImageView>(viewId)
            view.setImageBitmap(bitmap)
            return this
        }

        fun setOnClickListener(@IdRes viewId: Int, onClickListener: View.OnClickListener): BaseViewHolder {
            val view = getView<View>(viewId)
            view.setOnClickListener(onClickListener)
            return this
        }

    }
}