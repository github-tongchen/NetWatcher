package com.tongchen.basemodule

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.tongchen.basemodule.base.BaseDialogFragment
import com.tongchen.basemodule.databinding.BaseModuleFragmentConfirmDialogBinding
import kotlinx.android.synthetic.main.base_module_fragment_confirm_dialog.*


/**
 * @author TongChen
 * @date 2019/11/20  14:24
 * <p>
 * Desc:
 */
class ConfirmDialogFragment : BaseDialogFragment<BaseModuleFragmentConfirmDialogBinding>(), View.OnClickListener {

    private val TAG = "ConfirmDialogFragment"

    private lateinit var mDialogBean: DialogBean
    private var mOnPositiveListener: OnPositiveListener? = null
    private var mOnNegativeListener: OnNegativeListener? = null

    companion object {
        fun newInstance(): ConfirmDialogFragment {
            return ConfirmDialogFragment()
        }
    }

    init {
        mDialogBean = DialogBean()
    }

    override fun getLayoutId(): Int = R.layout.base_module_fragment_confirm_dialog

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mDataBinding.dialogBean = mDialogBean

        tv_negative.setOnClickListener(this)
        tv_positive.setOnClickListener(this)
    }

    fun setTitle(titleStr: String): ConfirmDialogFragment {
        mDialogBean.titleStr = titleStr
        return this
    }

    fun setContent(contentStr: String): ConfirmDialogFragment {
        mDialogBean.contentStr = contentStr
        return this
    }

    fun setPositiveBtn(positiveStr: String, listener: OnPositiveListener): ConfirmDialogFragment {
        mDialogBean.positiveStr = positiveStr
        mOnPositiveListener = listener
        return this
    }

    fun setNegativeBtn(negativeStr: String, listener: OnNegativeListener): ConfirmDialogFragment {
        mDialogBean.negativeStr = negativeStr
        mOnNegativeListener = listener
        return this
    }

    override fun onClick(v: View) {
        if (v === tv_negative) {
            if (mOnNegativeListener != null) {
                mOnNegativeListener!!.onClick(this@ConfirmDialogFragment)
            }
        } else if (v === tv_positive) {
            if (mOnPositiveListener != null) {
                mOnPositiveListener!!.onClick(this@ConfirmDialogFragment)
            }
        }
    }

    interface OnPositiveListener {
        fun onClick(dialog: DialogFragment?)
    }

    interface OnNegativeListener {
        fun onClick(dialog: DialogFragment?)
    }


    data class DialogBean constructor(
        var titleStr: String,
        var contentStr: String,
        var negativeStr: String,
        var positiveStr: String
    ) {
        constructor() : this("", "", "", "")
    }
}

