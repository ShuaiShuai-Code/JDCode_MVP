package com.jd.lie.mine.utils

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PhotoUtilKt {
    fun uriToFile(uri: Uri, context: Context): File? {
        var path: String? = null
        if ("file" == uri.scheme) {
            path = uri.encodedPath
            if (path != null) {
                path = Uri.decode(path)
                val cr: ContentResolver = context.getContentResolver()
                val buff = StringBuffer()
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                    .append("'$path'").append(")")
                val cur: Cursor? = cr.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    arrayOf(
                        MediaStore.Images.ImageColumns._ID,
                        MediaStore.Images.ImageColumns.DATA
                    ),
                    buff.toString(),
                    null,
                    null
                )
                var index = 0
                var dataIdx = 0
                cur?.moveToFirst()
                if (cur != null) {
                    while (!cur.isAfterLast()) {
                        index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID)
                        index = cur.getInt(index)
                        dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                        path = cur.getString(dataIdx)
                        cur.moveToNext()
                    }
                }
                cur?.close()
                if (index == 0) {
                } else {
                    val u =
                        Uri.parse("content://media/external/images/media/$index")
                    println("temp uri is :$u")
                }
            }
            if (path != null) {
                return File(path)
            }
        } else if ("content" == uri.scheme) {
            // 4.2.2以后
            var pathStr = uri.path
            if (pathStr != null) {
                //7.0路径适配问题
                if (pathStr.startsWith("/files_path")) {
                    pathStr = pathStr.replace(
                        "/files_path",
                        Environment.getExternalStorageDirectory().path
                    )
                }
                return File(pathStr)
            }
            return null
        } else {

        }
        return null
    }

     fun createFileBody(pic: String): MultipartBody.Part? {
        val file = File(pic)
        val requestBody: RequestBody =
            file.asRequestBody("application/x-www-form-urlencoded; charset=UTF-8".toMediaType())
        return MultipartBody.Part.createFormData("file", file.name, requestBody)
    }
}