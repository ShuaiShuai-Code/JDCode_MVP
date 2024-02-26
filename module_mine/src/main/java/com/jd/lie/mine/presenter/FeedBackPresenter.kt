package com.jd.lie.mine.presenter

import com.bumptech.glide.Glide
import com.jd.common.mvp.BasePresenter
import com.jd.lie.mine.network.api.CqtService
import com.jd.lie.mine.network.entity.UserEntity
import com.jd.utils.LogUtils
import com.jd.utils.ToastUtils
import com.mp5a5.www.library.use.BaseObserver
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_head_updata.*
import okhttp3.MultipartBody

/**
 * @describe ：
 * @author ：王文彬 on 2020/5/20 15：08
 * @email ：wangwenbin29@jd.com
 */
class FeedBackPresenter : BasePresenter<FeedBackContract.View>(), FeedBackContract.Presenter {
    override fun uploadPic(
        activity: RxAppCompatActivity,
        file: List<MultipartBody.Part>?
    ) {
        CqtService
            .uploadPic(file)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.compose(activity.bindToLifecycle())
            ?.subscribe(object : BaseObserver<UserEntity>() {
                override fun onRequestStart() {
                    v.showLoading()
                }

                override fun onRequestEnd() {
                    v.hideLoading()
                }

                override fun onSuccess(response: UserEntity?) {
                    v.showData(response?.token)
                    ToastUtils.show(response!!.msg)

                }

                override fun onFailing(response: UserEntity?) {
                    super.onFailing(response)
                    v.showEmptyView()
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    v.showEmptyView()
                }


            })
    }

    override fun feedback(
        activity: RxAppCompatActivity,
        versionNumber: String?,
        feedbackType: String?,
        feedbackContent: String?,
        urls: List<String>
    ) {
        CqtService
            .feedback(versionNumber,feedbackType,feedbackContent,urls)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.compose(activity.bindToLifecycle())
            ?.subscribe(object : BaseObserver<UserEntity>() {
                override fun onRequestStart() {
                    v.showLoading()
                }

                override fun onRequestEnd() {
                    v.hideLoading()
                }

                override fun onSuccess(response: UserEntity?) {
                    v.showData(response?.token)
                    ToastUtils.show(response!!.msg)

                }

                override fun onFailing(response: UserEntity?) {
                    super.onFailing(response)
                    v.showEmptyView()
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    v.showEmptyView()
                }


            })
    }


}







