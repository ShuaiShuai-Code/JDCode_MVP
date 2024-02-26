package com.jd.lie.mine.ui.activity

import android.content.Intent
import com.bumptech.glide.Glide
import com.jd.common.ui.activity.BaseActivity
import com.jd.common.ui.activity.BaseMvpActivity
import com.jd.lie.mine.R
import com.jd.lie.mine.presenter.UpdataContract
import com.jd.lie.mine.presenter.UpdataPresenter
import com.jd.lie.mine.presenter.UploadPresenter
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_user_infor.*

/**
 * 用户信息
 */
class UserInforActivity : BaseActivity() {
    override fun initLayout(): Int = R.layout.activity_user_infor


    override fun needHeader(): Boolean {
        return true
    }

    override fun chooseHeader(): Header {
        return Header.SUB
    }


    override fun initView() {
        super.initView()
        setTvSubLeftTitle("用户信息")
        Glide.with(thisActivity)
            .load("http://storage.jd.com/liedetector-photo-public/20200609100413982195.png")
            .into(img_head)
    }

    override fun initListener() {
        super.initListener()
        //头像
        tv_header.setOnClickListener {
            startActivity(Intent(this, HeadUpdataActivity::class.java))
        }
        //机构
        tv_mech.setOnClickListener {
            var intent = Intent(this, UpdataInforActivity::class.java)
            intent.putExtra("title", "修改机构")
            startActivity(intent)
        }
        //电话
        tv_phone.setOnClickListener {
            var intent = Intent(this, UpdataInforActivity::class.java)
            intent.putExtra("title", "修改电话")
            startActivity(intent)
        }
    }


}