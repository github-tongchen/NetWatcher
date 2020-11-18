package com.tongchen.gank.biz.ui.fragment

import android.os.Bundle
import com.tongchen.gank.R
import com.tongchen.gank.base.GankBaseDBFragment
import com.tongchen.gank.biz.entity.GankResult
import com.tongchen.gank.databinding.ModuleGankFragmentTextBinding

/**
 * @author TongChen
 * @date 2019/11/25  18:19
 * <p>
 * Desc:
 */
class ContentPicFragment : GankBaseDBFragment<ModuleGankFragmentTextBinding>() {

//    val TAG = "ContentPicFragment"

    val PERS_EXTERNAL_STORAGE_CODE = 100

    private var mGankResult: GankResult? = null
//    private lateinit var mConfirmDialogFragment: ConfirmDialogFragment

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

    /* override fun onActivityCreated(savedInstanceState: Bundle?) {
         super.onActivityCreated(savedInstanceState)
         val activity = mActivity
         if (activity != null) {
             Glide.with(activity).load(mGankResult?.url).into(iv_pic)
         }

         mConfirmDialogFragment = ConfirmDialogFragment.newInstance()
         mConfirmDialogFragment.setOnDialogClickListener(object : ConfirmDialogFragment.OnDialogClickListener {
             override fun onPositive() {
                 if (checkPermission()) {
                     savePic2Local()
                     mConfirmDialogFragment.dismiss()
                 } else {
                     requestPermission()
                 }
             }

             override fun onNegative() {
                 mConfirmDialogFragment.dismiss()
             }
         })

         iv_pic.setOnLongClickListener {
             mConfirmDialogFragment.show(fragmentManager, "ConfirmDialogFragment")
             true
         }
     }

     override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults)

         EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
     }

     private fun checkPermission(): Boolean {
         val activity = mActivity
         return if (activity == null) {
             false
         } else {
             EasyPermissions.hasPermissions(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
         }
     }

     private fun requestPermission() {
         EasyPermissions.requestPermissions(this,
                 String.format(getString(R.string.permission_save_pic) + getString(R.string.permission_grant_request), Manifest.permission.READ_EXTERNAL_STORAGE + "\n" + Manifest.permission.WRITE_EXTERNAL_STORAGE),
                 PERS_EXTERNAL_STORAGE_CODE,
                 Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
     }


     override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
         val sb = StringBuilder()
         for (str in perms) {
             sb.append(str)
             sb.append("\n")
         }
         when (requestCode) {
             PERS_EXTERNAL_STORAGE_CODE -> {
                 ToastUtils.showShort(String.format(getString(R.string.permission_grant_succeed), sb))
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
                 ToastUtils.showShort(String.format(getString(R.string.permission_grant_failed), sb))
             }
         }

         if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
             ToastUtils.showShort(String.format(getString(R.string.permission_grant_failed_no_ask), sb))
             AppSettingsDialog.Builder(this)
                     .setRationale(String.format(getString(R.string.permission_grant_failed_2_setting_tip), sb))
                     .setPositiveButton(R.string.permission_grant_failed_2_setting_positive)
                     .setNegativeButton(R.string.permission_grant_failed_2_setting_negative)
                     .build()
                     .show()
         }
     }

     @SuppressLint("CheckResult")
     private fun savePic2Local() {
         val activity = mActivity
         if (activity == null) {
             ToastUtils.showShort(R.string.save_pic_failed)
             return
         }
         Observable.create(object : ObservableOnSubscribe<File> {
             override fun subscribe(emitter: ObservableEmitter<File>) {
                 emitter.onNext(
                         Glide.with(activity)
                                 .downloadOnly()
                                 .load(mGankResult?.url)
                                 .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                 .get())
                 emitter.onComplete()
             }
         }).observeOn(AndroidSchedulers.mainThread())
                 .subscribeOn(Schedulers.io())
                 .subscribe(object : Observer<File> {
                     override fun onSubscribe(d: Disposable) {

                     }

                     override fun onNext(file: File) {
                         val picFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                         val appDir = File(picFolder, "/TWatcher/gank/")
                         if (!appDir.exists()) {
                             val mkSuccess = appDir.mkdirs()
                             if (!mkSuccess) {
                                 ToastUtils.showShort(R.string.save_pic_failed)
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
                         ToastUtils.showShort(R.string.save_pic_succeed)
                     }

                     override fun onError(e: Throwable) {
                         ToastUtils.showShort(R.string.save_pic_failed)
                     }
                 })
     }

     // 通知图库更新
     private fun refreshAlbum(destFile: File) {
         val uri = Uri.fromFile(destFile)
         mActivity?.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
     }*/

}