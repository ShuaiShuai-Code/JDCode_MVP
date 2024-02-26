package com.jd.lie.mine.presenter

import com.jd.common.mvp.BasePresenter
import com.jd.lie.mine.network.api.CqtService
import com.jd.lie.mine.network.entity.AddRelateProblemEntity
import com.mp5a5.www.library.use.BaseObserver
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @describe ：
 * @author ：王文彬 on 2020/5/20 15：08
 * @email ：wangwenbin29@jd.com
 */
class CqtPresenter : BasePresenter<CqtContract.View>(), CqtContract.Presenter {
  
  override fun getAddRelateProblem(
      activity: RxAppCompatActivity,
      dimensionId: String?,
      crimeId: String?,
      questionType: String?,
      index: Int
  ) {

    CqtService
        .getAddProblemList(dimensionId, crimeId, questionType, index)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(activity.bindToLifecycle())
        .subscribe(object : BaseObserver<AddRelateProblemEntity>() {
          override fun onRequestStart() {
            v.showLoading()
          }

          override fun onRequestEnd() {
            v.hideLoading()
          }

          override fun onSuccess(response: AddRelateProblemEntity?) {
            v.showData(response?.resData)
          }

          override fun onFailing(response: AddRelateProblemEntity?) {
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