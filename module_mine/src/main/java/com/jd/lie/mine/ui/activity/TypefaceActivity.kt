package com.jd.lie.mine.ui.activity

import android.content.Intent
import com.jd.common.ui.activity.BaseActivity
import com.jd.lie.mine.R
import kotlinx.android.synthetic.main.activity_typeface.*

/**
 * 设置
 */
class TypefaceActivity : BaseActivity() {
    override fun initLayout(): Int = R.layout.activity_typeface


    override fun needHeader(): Boolean {
        return true
    }

    override fun chooseHeader(): Header {
        return Header.SUB
    }

    override fun initView() {
        super.initView()
        setTvSubLeftTitle("通用")
        tv_set.setOnClickListener {
            startActivity(Intent(this@TypefaceActivity, TypefaceSetActivity::class.java))
        }
    }


}