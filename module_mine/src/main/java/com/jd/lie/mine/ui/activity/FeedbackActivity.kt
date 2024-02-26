package com.jd.lie.mine.ui.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.widget.AdapterView
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.jd.common.ui.activity.BaseMvpActivity
import com.jd.lie.mine.R
import com.jd.lie.mine.picker.Picker
import com.jd.lie.mine.picker.engine.GlideEngine
import com.jd.lie.mine.picker.utils.PicturePickerUtils
import com.jd.lie.mine.presenter.UpdataContract
import com.jd.lie.mine.presenter.UploadContract
import com.jd.lie.mine.presenter.UploadPresenter
import com.jd.lie.mine.ui.adapter.AskGVAdapter
import com.jd.lie.mine.utils.DialogBottomChoose
import com.jd.lie.mine.utils.MyOnClickListenter
import com.jd.lie.mine.utils.PhotoUtilKt
import com.jd.lie.mine.utils.PhotoUtils
import com.jd.utils.LogUtils
import com.jd.utils.ToastUtils
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_feedback.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


/**
 * 设置
 */
class FeedbackActivity : BaseMvpActivity<UploadPresenter>(), UploadContract.View,
    MyOnClickListenter {

    private var dm: DisplayMetrics? = null


    /***
     * 图片路径列表
     */
    private var listFiles: MutableList<String>? = null

    /***
     * 图片路径列表
     */
    private var mSelected_url: MutableList<String>? = null

    /***
     * 查看大图删除后返回activity
     */
    private val RESULT_DEL = 1

    /***
     * 相机或图库操作后返回activity
     */
    private val RESULT_REQUEST_CODE = 3

    /***
     * 相机或图库操作后返回的数据uri
     */
    private var imageUri: Uri? = null
    lateinit var adapter: AskGVAdapter

    private lateinit var photoUtilKt: PhotoUtilKt
    private lateinit var dialog: DialogBottomChoose

    private var mSelected: MutableList<Uri>? = null
    override fun needHeader(): Boolean {
        return true
    }

    private var listUrl: MutableList<String>? = null
    override fun chooseHeader(): Header {
        return Header.SUB
    }

    private var isSubmit: Boolean = false

    override fun initLayout(): Int = R.layout.activity_feedback
    override fun initView() {
        super.initView()
        setTvSubLeftTitle("问题反馈")
        dialog = DialogBottomChoose(thisActivity, this)
        photoUtilKt = PhotoUtilKt()
        listFiles = ArrayList()
        mSelected_url = ArrayList()
        listUrl = ArrayList()
        dm = DisplayMetrics()
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        adapter = AskGVAdapter(this, dm!!.widthPixels / 5)
        adapter.setMaxImg(5)
        gridview.setAdapter(adapter);

    }

    override fun <E> showData(data: E) {
        super.showData(data)
        listUrl?.add(data.toString())
        LogUtils.w("提交成功")
        if (isSubmit)
            finish()
    }

    private val STORAGE_PERMISSIONS_REQUEST_CODE = 0x04
    private var location2 = 0
    override fun initListener() {
        super.initListener()
        gridview.setOnItemClickListener(AdapterView.OnItemClickListener { arg0, arg1, location, arg3 ->
            // TODO Auto-generated method stub
            if (ContextCompat.checkSelfPermission(
                    thisActivity,
                    Manifest.permission.CAMERA
                ) !== PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                    thisActivity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) !== PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                    thisActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    thisActivity, arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    STORAGE_PERMISSIONS_REQUEST_CODE
                )
                location2 = location
            } else {
                if (listFiles!!.size == 0) {
                    selectImg()
                } else if (gridview.getCount() <= 5) {
                    if (location == gridview.getCount() - 1) {
                        if (listFiles!!.size != gridview.getCount() - 1) {
                            //checkImg(location)
                        } else {
                            selectImg()
                        }
                    }
                }
            }
        })



        tv_submit.setOnClickListener {
            if (tv_type.text.toString().isEmpty()) {
                ToastUtils.show("请选择问题类型")
                return@setOnClickListener
            }
            if (edit_text.text.toString().isEmpty()) {
                ToastUtils.show("请输入您要反馈的问题")
                return@setOnClickListener
            }
            listUrl?.let { it1 ->
                isSubmit = true
                presenter.feedback(
                    thisActivity as RxAppCompatActivity,
                    "1.0",
                    tv_type.text.toString(),
                    edit_text.text.toString(),
                    it1
                )
            }

        }
        tv_type.setOnClickListener {
            dialog.show()
        }
    }

    override fun getBasePresenter(): UploadPresenter {
        return UploadPresenter()
    }

    private fun selectImg() {
        Picker.from(thisActivity).count(5).enableCamera(true).setEngine(GlideEngine())
            .resume(mSelected).forResult(RESULT_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === RESULT_REQUEST_CODE && resultCode === Activity.RESULT_OK) {
            try {
                if (android.R.attr.data != null) {
                    mSelected = PicturePickerUtils.obtainResult(data)
                    listFiles!!.clear()
                    mSelected_url!!.clear()
                    if (mSelected != null) {
                        for (i in mSelected!!.indices) {
                            imageUri = mSelected!![i]
                            if (imageUri != null) {
                                mSelected_url!!.add(imageUri.toString())
                                val newUri = Uri.parse(
                                    getRealPathFromUri(imageUri)
                                )
                                listFiles!!.add(newUri.path)
                            }
                        }
                    }
                    initListView()
                }
            } catch (e: Exception) {
            }
        } else if (requestCode === RESULT_DEL && resultCode === RESULT_DEL) {
            val stringList: List<String> =
                data!!.getStringArrayListExtra("image")
            listFiles?.clear()
            listFiles?.addAll(stringList)
            mSelected_url = data.getStringArrayListExtra("image2")
            mSelected?.clear()
            for (pos in 0 until mSelected_url!!.size) {
                mSelected?.add(Uri.parse(mSelected_url?.get(pos)))
            }
            initListView()

        }
    }

    /***
     * 设置图片控件列表
     */
    private fun initListView() {

        adapter.setListData(listFiles)

        for (item: String in this!!.listFiles!!) {
            val listFilePart: MutableList<MultipartBody.Part> =
                java.util.ArrayList()
            LogUtils.w("item" + "-------------------" + item)
            var newUri = Uri.parse(item)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                newUri = FileProvider.getUriForFile(
                    applicationContext,
                    "com.jd.lie.mine.fileprovider",
                    File(newUri.path)
                )
            }

            val filestr = PhotoUtils.compressBitmapToFile(
                PhotoUtils.getBitmapFromUri(
                    newUri,
                    thisActivity
                ), thisActivity
            )
            LogUtils.w("filestr" + "-------------------" + filestr)
            if (filestr != null) {
//                photoUtilKt.createFileBody(filestr.toString())?.let { list.add(it) }
                listFilePart.add(photoUtilKt.createFileBody(filestr.toString())!!)
            }
            //一次一次提交图片，成功后存到list
            LogUtils.w("filestr" + "-------------------" + listFilePart.size)
            presenter.uploadPic(thisActivity as RxAppCompatActivity, listFilePart)
        }

    }

    fun getRealPathFromUri(contentUri: Uri?): String? {
        var cursor: Cursor? = null
        return try {
            val proj =
                arrayOf(MediaStore.Images.Media.DATA)
            cursor = contentResolver.query(contentUri, proj, null, null, null)
            val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } finally {
            if (cursor != null) {
                cursor.close()
            }
        }
    }

    override fun setString(str: String) {
        tv_type.setText(str)
    }


}




