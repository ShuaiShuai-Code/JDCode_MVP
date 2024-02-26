package com.jd.lie.mine.presenter

import com.jd.common.mvp.BaseView
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

/**
 * @describe ：
 * @author ：王文彬 on 2020/5/20 15：03
 * @email ：wangwenbin29@jd.com
 */
interface UpdataContract {

    interface View : BaseView {
        fun <E> showData(data: E) {}
        fun showLoading() {}
        fun hideLoading() {}
        fun showEmptyView() {}
    }

    interface Presenter {
        fun updata(
            activity: RxAppCompatActivity,
            photoUrl: String?,
            phoneNumber: String?
        )
    }

}