package com.jd.lie.mine.presenter

import android.widget.ImageView
import android.widget.TextView
import com.jd.common.mvp.BaseView
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import okhttp3.MultipartBody
import java.io.File

/**
 * @describe ：
 * @author ：王文彬 on 2020/5/20 15：03
 * @email ：wangwenbin29@jd.com
 */
interface UploadContract {

    interface View : BaseView {
        fun <E> showData(data: E) {}
        fun showLoading() {}
        fun hideLoading() {}
        fun showEmptyView() {}
    }

    interface Presenter {
        fun uploadPic(
            activity: RxAppCompatActivity,
            file: List<MultipartBody.Part>?
        )

        fun feedback(
            activity: RxAppCompatActivity,
            versionNumber: String?,
            feedbackType: String?,
            feedbackContent: String?,
            urls:List<String>
        )
    }

}