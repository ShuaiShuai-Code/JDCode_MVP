package com.jd.lie.mine.ui.activity

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.jd.common.ui.activity.BaseActivity
import com.jd.common.ui.activity.BaseMvpActivity
import com.jd.lie.mine.BuildConfig
import com.jd.lie.mine.R
import com.jd.lie.mine.network.entity.VersionEntity
import com.jd.lie.mine.presenter.UpdataVersionContract
import com.jd.lie.mine.presenter.UpdataVersionPresenter
import com.jd.lie.mine.utils.VersionUtils
import com.jd.utils.AppManagerUtils
import com.jd.utils.StringUtils
import com.jd.utils.ToastUtils
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_about.*

/**
 * 关于我们
 */
class AboutActivity : BaseMvpActivity<UpdataVersionPresenter>(), UpdataVersionContract.View {
    override fun initLayout(): Int = R.layout.activity_about


    override fun needHeader(): Boolean {
        return true
    }

    override fun <E> showData(data: E) {
        super.showData(data)
        if (null != data) {
           if (data is VersionEntity)
            ToastUtils.show("有新版本"+data.resData.versionUrl)

//            txt.text = "有新版发布"
//            txt.setTextColor(
//                ContextCompat.getColor(
//                    this, R.color.color_0780ED
//                )
//            )
        } else {
            ToastUtils.show("已是最新版本")
//            txt.text = "已是最新版本"
//            txt.setTextColor(
//                ContextCompat.getColor(
//                    this, R.color.color_999999
//                )
//            )
        }
    }

    override fun chooseHeader(): Header {
        return Header.SUB
    }

    override fun initView() {
        super.initView()
        setTvSubLeftTitle("关于我们")
    }

    override fun initListener() {
        super.initListener()
        tv_check_updata.setOnClickListener {
           // ToastUtils.show("正在获取最新版本信息")
            presenter.getVersion(thisActivity as RxAppCompatActivity, "1.0")
        }
    }


    override fun getBasePresenter(): UpdataVersionPresenter {
        return UpdataVersionPresenter()
    }

}