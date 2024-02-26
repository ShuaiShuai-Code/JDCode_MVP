package com.jd.lie.mine.presenter

import com.jd.common.mvp.BasePresenter
import com.jd.lie.mine.network.api.CqtService
import com.jd.lie.mine.network.entity.AddRelateProblemEntity
import com.jd.lie.mine.network.entity.UserEntity
import com.jd.utils.LogUtils.w
import com.mp5a5.www.library.use.BaseObserver
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @describe ：
 * @author ：王文彬 on 2020/5/20 15：08
 * @email ：wangwenbin29@jd.com
 */
class UpdataPresenter : BasePresenter<UpdataContract.View>(), UpdataContract.Presenter {

    override fun updata(
        activity: RxAppCompatActivity,
        photoUrl: String?,
        phoneNumber: String?
    ) {

        CqtService
            .update(photoUrl, phoneNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(activity.bindToLifecycle())
            .subscribe(object : BaseObserver<UserEntity>() {
                override fun onRequestStart() {
                    v.showLoading()
                }

                override fun onRequestEnd() {
                    v.hideLoading()
                }

                override fun onSuccess(response: UserEntity?) {
                    v.showData(response?.resMessage)
                    activity.finish()

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