package com.jd.lie.mine.ui.activity

import com.jd.common.ui.activity.BaseActivity
import com.jd.lie.mine.R

/**
 * 功能介绍
 */
class FunctionActivity : BaseActivity() {
    override fun initLayout(): Int = R.layout.activity_function

    override fun needHeader(): Boolean {
        return true
    }

    override fun chooseHeader(): Header {
        return Header.SUB
    }

    override fun initView() {
        super.initView()
        setTvSubLeftTitle("功能介绍")
    }

}