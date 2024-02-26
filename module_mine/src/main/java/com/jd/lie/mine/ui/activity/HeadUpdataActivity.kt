package com.jd.lie.mine.ui.activity

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.jd.common.ui.activity.BaseMvpActivity
import com.jd.lie.mine.BuildConfig
import com.jd.lie.mine.R
import com.jd.lie.mine.presenter.UploadContract
import com.jd.lie.mine.presenter.UploadPresenter
import com.jd.lie.mine.utils.PhotoUtilKt
import com.jd.lie.mine.utils.PhotoUtils
import com.jd.utils.LogUtils
import com.jd.utils.ToastUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_head_updata.*
import okhttp3.MultipartBody
import java.io.File
import java.lang.Exception


/**
 * 设置
 */
class HeadUpdataActivity : BaseMvpActivity<UploadPresenter>(), UploadContract.View {


    override fun initLayout(): Int = R.layout.activity_head_updata
    private val CODE_GALLERY_REQUEST = 0xa0
    private lateinit var photoUtilKt: PhotoUtilKt
    override fun needHeader(): Boolean {
        return true
    }

    override fun chooseHeader(): Header {
        return Header.SUB
    }

    override fun initListener() {
        super.initListener()
        tv_select.setOnClickListener {
            testRequestEachCombined(1)

        }
        tv_photo.setOnClickListener {
            testRequestEachCombined(2)
        }
    }

    override fun <E> showData(data: E) {
        super.showData(data)
        Glide.with(thisActivity).load(data).into(img_head)

    }

    override fun initView() {
        super.initView()
        photoUtilKt = PhotoUtilKt()
        setTvSubLeftTitle("设置个人头像")
        var url =
            "http://storage.jd.com/liedetector-photo-public/20200609100413982195.png"
        Glide.with(this).load(url).into(img_head)

    }

    private val REQUEST_CAPTURE = 92 //拍照

    private val OUTPUT_X = 400
    private val fileUri: File =
        File("/data/data/" + BuildConfig.APPLICATION_ID + "/photo.jpg")
    private val fileCropUri: File =
        File("/data/data/" + BuildConfig.APPLICATION_ID + "/crop_photo.jpg")
    private val cameraPhotoPath: File =
        File(
            Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .getAbsolutePath() + File.separator + "jd/" + System.currentTimeMillis() + ".jpg"
        )
    private var imageUri: Uri? = null
    fun testRequestEachCombined(intType: Int): Boolean {
        val rxPermissions = RxPermissions(this)
        rxPermissions
            .request(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .subscribe { granted: Boolean ->
                if (granted) {
                    // All requested permissions are granted
                    if (intType == 1) {
                        PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
                    } else {
                        openCamera()
                    }
                } else {
                    // At least one permission is denied
                    ToastUtils.show("At least one permission is denied")
                }
            }
        return false
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CAPTURE -> {//拍照
                    val list: MutableList<MultipartBody.Part> =
                        java.util.ArrayList()

                    val bitmap = BitmapFactory.decodeStream(this!!.imageUri?.let {
                        contentResolver.openInputStream(
                            it
                        )
                    })
                    val filestr = PhotoUtils.compressBitmapToFile(bitmap, thisActivity)

                    if (filestr != null) {
                        photoUtilKt.createFileBody(filestr.toString())?.let { list.add(it) }
                    }

                    presenter.uploadPic(thisActivity as RxAppCompatActivity, list)

                }
                CODE_GALLERY_REQUEST -> if (PhotoUtils.hasSdcard()) {//相册

                    var newUri = Uri.parse(
                        PhotoUtils.getPath(
                            this,
                            data?.getData()
                        )
                    )
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        newUri = FileProvider.getUriForFile(
                            applicationContext,
                            "com.jd.lie.mine.fileprovider",
                            File(newUri.path)
                        )
                    }

                    val list: MutableList<MultipartBody.Part> =
                        java.util.ArrayList()
                    val filestr = PhotoUtils.compressBitmapToFile(
                        PhotoUtils.getBitmapFromUri(
                            newUri,
                            thisActivity
                        ), thisActivity
                    )
                    LogUtils.w("filestr" + "-------------------" + filestr)
                    if (filestr != null) {
                        photoUtilKt.createFileBody(filestr.toString())?.let { list.add(it) }
                    }

                    presenter.uploadPic(thisActivity as RxAppCompatActivity, list)

                } else {
                    PhotoUtils.openPic(this, CODE_GALLERY_REQUEST)
                }

            }

        }

    }

    override fun getBasePresenter(): UploadPresenter {
        return UploadPresenter()
    }


    private fun openCamera() {
        var outputImage = File(externalCacheDir, "output_image.jpg")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            imageUri = Uri.fromFile(outputImage)
        } else {
            imageUri = FileProvider.getUriForFile(
                thisActivity,
                "com.jd.lie.mine.fileprovider",
                outputImage
            )
        }

        val intent = Intent()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        intent.action = MediaStore.ACTION_IMAGE_CAPTURE //设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri) //将拍取的照片保存到指定URI
        startActivityForResult(intent, REQUEST_CAPTURE)
    }


}














