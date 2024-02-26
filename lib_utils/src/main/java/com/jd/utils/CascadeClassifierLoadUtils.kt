package com.jd.utils

import android.content.Context
import androidx.annotation.RawRes
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * @describe ：opencv记载级联器的工具类
 * @author ：王文彬 on 2020/4/9 13：39
 * @email ：wangwenbin29@jd.com
 */
class CascadeClassifierLoadUtils {
  
  companion object {
    
    fun getLoadRawResourcePath(
        context: Context, @RawRes rawId: Int,
        fileChildName: String
    ): String? {
      
      try {
        val inputStream = context.resources.openRawResource(rawId)
        val cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE)
        val file = File(cascadeDir, fileChildName)
        val fileOutputStream = FileOutputStream(file)
        val bytes = ByteArray(4096)
        var bytesRead: Int
        while (inputStream.read(bytes).also { bytesRead = it } != -1) {
          fileOutputStream.write(bytes, 0, bytesRead)
        }
        inputStream.close()
        fileOutputStream.close()
        return file.absolutePath
      } catch (e: IOException) {
        e.printStackTrace()
      }
      
      return null
    }
  }
}