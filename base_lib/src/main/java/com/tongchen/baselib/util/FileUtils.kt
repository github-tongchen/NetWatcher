package com.tongchen.baselib.util

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by TongChen at 16:25 on 2018/12/26.
 *
 *
 * Description: 文件操作工具类
 */
class FileUtils private constructor() {

    init {
        throw UnsupportedOperationException("FileUtils doesn't need to be initialized!")
    }

    companion object {
        fun copyFile2Target(source: File, target: File) {
            var fileInputStream: FileInputStream? = null
            var fileOutputStream: FileOutputStream? = null
            try {
                fileInputStream = FileInputStream(source)
                fileOutputStream = FileOutputStream(target)
                val buffer = ByteArray(1024)
                while (fileInputStream.read(buffer) > 0) {
                    fileOutputStream.write(buffer)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    fileInputStream?.close()
                    fileOutputStream?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}