package com.jd.lie.mine.presenter

import com.jd.common.mvp.BasePresenter
import com.jd.lie.mine.network.api.CqtService
import com.jd.lie.mine.network.entity.AddRelateProblemEntity
import com.jd.lie.mine.network.entity.UserEntity
import com.jd.lie.mine.network.entity.VersionEntity
import com.mp5a5.www.library.use.BaseObserver
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @describe ：
 * @author ：王文彬 on 2020/5/20 15：08
 * @email ：wangwenbin29@jd.com
 */
class UpdataVersionPresenter : BasePresenter<UpdataVersionContract.View>(),
    UpdataVersionContract.Presenter {

    override fun getVersion(
        activity: RxAppCompatActivity,
        versionNumber: String?
    ) {
        CqtService
            .updateVersion(versionNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(activity.bindToLifecycle())
            .subscribe(object : BaseObserver<VersionEntity>() {
                override fun onRequestStart() {
                    v.showLoading()
                }

                override fun onRequestEnd() {
                    v.hideLoading()
                }

                override fun onSuccess(response: VersionEntity?) {
                    v.showData(response)
                }

                override fun onFailing(response: VersionEntity?) {
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