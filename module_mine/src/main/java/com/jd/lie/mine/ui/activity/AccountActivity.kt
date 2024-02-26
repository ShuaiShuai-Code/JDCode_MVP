package com.jd.lie.mine.ui.activity

import com.jd.common.ui.activity.BaseActivity
import com.jd.lie.mine.R

/**
 * 账户安全
 */
class AccountActivity : BaseActivity() {
    override fun initLayout(): Int = R.layout.activity_account


    override fun needHeader(): Boolean {
        return true
    }

    override fun chooseHeader(): Header {
        return Header.SUB
    }

    override fun initView() {
        super.initView()
        setTvSubLeftTitle("账户安全")

    }


}