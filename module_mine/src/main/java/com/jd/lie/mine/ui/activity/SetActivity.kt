package com.jd.lie.mine.ui.activity

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import com.jd.common.ui.activity.BaseMvpActivity
import com.jd.lie.mine.R
import com.jd.lie.mine.presenter.LoginContract
import com.jd.lie.mine.presenter.LoginPresenter
import com.jd.lie.mine.utils.CacheUtil
import com.jd.utils.ToastUtils
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_set.*
import kotlinx.android.synthetic.main.fragment_mine.tv_gn

/**
 * 设置-测试登录接口
 */
class SetActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.View {
    override fun initLayout(): Int = R.layout.activity_set


    override fun needHeader(): Boolean {
        return true
    }

    override fun chooseHeader(): Header {
        return Header.SUB
    }


    override fun initView() {
        super.initView()
        setTvSubLeftTitle("设置")
        initNet()
        try {
            val catch1: String = CacheUtil.getTotalCacheSize(this)
            tv_cache.setText(catch1)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        tv_cache.setOnClickListener {
            CacheUtil.clearAllCache(this)
            ToastUtils.show("清理成功")
            tv_cache.setText("")
        }
    }

    override fun initNet() {
        super.initNet()
        //测试登录接口
        //presenter.login(this as RxAppCompatActivity, "123456", "123456")
    }


    override fun initListener() {
        super.initListener()

        tv_gn.setOnClickListener {
            startActivity(Intent(this, TypefaceActivity::class.java))
        }

        tv_account.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))
        }
        tv_about.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }
//
//    override fun getResources(): Resources {
//        val res = super.getResources()
//        val config: Configuration = res.configuration
//        config.fontScale = 1f //1 设置正常字体大小的倍数
//        res.updateConfiguration(config, res.displayMetrics)
//        return res
//    }

    override fun getBasePresenter(): LoginPresenter {
        return LoginPresenter()
    }

}