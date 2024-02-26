package com.jd.lie.main

import android.Manifest
import com.alibaba.android.arouter.launcher.ARouter
import com.jd.common.ui.activity.BaseActivity
import com.jd.common.utils.RouterPathUtils
import com.jd.utils.LogUtils
import com.jd.utils.SnackbarUtil
import com.jd.utils.ToastUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun initLayout(): Int = R.layout.activity_main
    private var num = 1
    override fun initListener() {
        super.initListener()
        rtnQrCode.setOnClickListener {
            RxPermissions(thisActivity).requestEach(Manifest.permission.CAMERA).subscribe {
                if (it.granted) {
                    RouterPathUtils.gotoActivity(RouterPathUtils.QR_CODE, thisActivity, false)
                } else {
                    SnackbarUtil.shortSnackbar(clContent, "请您打开照相机权限", SnackbarUtil.Info)
                }
            }
        }

        rtnQrCode2.setOnClickListener {
           LogUtils.d("hello")

        }
    }
}
