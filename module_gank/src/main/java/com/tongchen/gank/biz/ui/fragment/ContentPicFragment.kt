package com.tongchen.gank.biz.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.tongchen.baselib.util.FileUtils
import com.tongchen.baselib.util.LifecycleUtils
import com.tongchen.baselib.util.ToastUtils
import com.tongchen.basemodule.ConfirmDialogFragment
import com.tongchen.gank.R
import com.tongchen.gank.base.GankBaseDBFragment
import com.tongchen.gank.biz.entity.GankResult
import com.tongchen.gank.databinding.ModuleGankFragmentContentPicBinding
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.module_gank_fragment_content_pic.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

/**
 * @author TongChen
 * @date 2019/11/25  18:19
 * <p>
 * Desc:
 */
class ContentPicFragment : GankBaseDBFragment<ModuleGankFragmentContentPicBinding>(), EasyPermissions.PermissionCallbacks {

    val TAG = "ContentPicFragment"

    val PERS_EXTERNAL_STORAGE_CODE = 100

    private var mGankResult: GankResult? = null
    private lateinit var mConfirmDialogFragment: ConfirmDialogFragment

    companion object {
        private const val ARG_GANK_RESULT = "gank_result"

        fun newInstance(result: GankResult): ContentPicFragment {
            val fragment = ContentPicFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_GANK_RESULT, result)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mGankResult = arguments!!.getParcelable(ARG_GANK_RESULT)
        }
    }

    override fun getLayoutId() = R.layout.module_gank_fragment_content_pic

    override fun inject2Fragment() {
        dataBindingComponent()?.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mDataBinding.result = mGankResult

        mConfirmDialogFragment = ConfirmDialogFragment.newInstance()
        mConfirmDialogFragment.setTitle(getString(R.string.module_gank_confirm_dialog_title))
            .setContent(getString(R.string.module_gank_confirm_dialog_content))
            .setNegativeBtn(getString(R.string.module_gank_confirm_dialog_negative), object : ConfirmDialogFragment.OnNegativeListener {
                override fun onClick(dialog: DialogFragment?) {
                    mConfirmDialogFragment.dismiss()
                }
            })
            .setPositiveBtn(getString(R.string.module_gank_confirm_dialog_positive), object : ConfirmDialogFragment.OnPositiveListener {
                override fun onClick(dialog: DialogFragment?) {
                    if (checkPermission()) {
                        savePic2Local()
                        mConfirmDialogFragment.dismiss()
                    } else {
                        requestPermission()
                    }
                }
            })


        iv_pic.setOnLongClickListener {
            fragmentManager?.let { it1 -> mConfirmDialogFragment.show(it1, "ConfirmDialogFragment") }
            true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private fun checkPermission(): Boolean {
        return if (activity == null) {
            false
        } else {
            EasyPermissions.hasPermissions(activity!!, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    private fun requestPermission() {
        EasyPermissions.requestPermissions(
            this,
            String.format(
                getString(R.string.module_gank_permission_save_pic) + getString(R.string.module_gank_permission_grant_request),
                Manifest.permission.READ_EXTERNAL_STORAGE + "\n" + Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            PERS_EXTERNAL_STORAGE_CODE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }


    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        val sb = StringBuilder()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        when (requestCode) {
            PERS_EXTERNAL_STORAGE_CODE -> {
                ToastUtils.showShort(activity?.applicationContext, String.format(getString(R.string.module_gank_permission_grant_succeed), sb))
                savePic2Local()
                mConfirmDialogFragment.dismiss()
            }
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        //处理权限名字字符串
        val sb = StringBuilder()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }

        when (requestCode) {
            PERS_EXTERNAL_STORAGE_CODE -> {
                ToastUtils.showShort(activity?.applicationContext, String.format(getString(R.string.module_gank_permission_grant_failed), sb))
            }
        }

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            ToastUtils.showShort(activity?.applicationContext, String.format(getString(R.string.module_gank_permission_grant_failed_no_ask), sb))
            AppSettingsDialog.Builder(this)
                .setRationale(String.format(getString(R.string.module_gank_permission_grant_failed_2_setting_tip), sb))
                .setPositiveButton(R.string.module_gank_permission_grant_failed_2_setting_positive)
                .setNegativeButton(R.string.module_gank_permission_grant_failed_2_setting_negative)
                .build()
                .show()
        }
    }

    @SuppressLint("CheckResult")
    private fun savePic2Local() {
        if (activity == null) {
            ToastUtils.showShort(activity?.applicationContext, R.string.module_gank_save_pic_failed)
            return
        }
        Observable.create(object : ObservableOnSubscribe<File> {
            override fun subscribe(emitter: ObservableEmitter<File>) {
                emitter.onNext(
                    Glide.with(activity!!)
                        .downloadOnly()
                        .load(mGankResult?.url)
                        .submit(SIZE_ORIGINAL, SIZE_ORIGINAL)
                        .get()
                )
                emitter.onComplete()
            }
        }).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .`as`(LifecycleUtils.bindLifecycle(this@ContentPicFragment))
            .subscribe(object : Observer<File> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(file: File) {
                    val picFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    val appDir = File(picFolder, "/NetWatcher/gank/")
                    if (!appDir.exists()) {
                        val mkSuccess = appDir.mkdirs()
                        if (!mkSuccess) {
                            ToastUtils.showShort(activity?.applicationContext,R.string.module_gank_save_pic_failed)
                            return
                        }
                    }
                    val time = System.currentTimeMillis()
                    val fileName = "$time.jpg"
                    val destFile = File(appDir, fileName)
                    //  把Glide下载得到图片复制到定义好的目录中去
                    FileUtils.copyFile2Target(file, destFile)
                    refreshAlbum(destFile)
                }

                override fun onComplete() {
                    ToastUtils.showShort(activity?.applicationContext,R.string.module_gank_save_pic_succeed)
                }

                override fun onError(e: Throwable) {
                    ToastUtils.showShort(activity?.applicationContext,R.string.module_gank_save_pic_failed)
                }
            })
    }

    // 通知图库更新
    private fun refreshAlbum(destFile: File) {
        val uri = Uri.fromFile(destFile)
        activity?.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
    }

}