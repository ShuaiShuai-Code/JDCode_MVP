package com.jd.lie.robot

import android.app.Application
import android.util.ArrayMap
import com.alibaba.android.arouter.launcher.ARouter
import com.jd.common.constant.Constant
import com.jd.common.constant.GeneralUrl
import com.jd.utils.BuildConfig
import com.jd.utils.SpUtils
import com.jd.utils.UtilsConfig
import com.mp5a5.www.library.utils.ApiConfig

/**
 * @describe ：
 * @author ：王文彬 on 2020/5/11 18：24
 * @email ：wangwenbin29@jd.com
 */
class IApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        UtilsConfig.init(this)
        initNet()
        initARouter()
    }

    private fun initNet() {
        val headMap = ArrayMap<String, String>()
        headMap["Content-Type"] = "application/x-www-form-urlencoded"
       val build = ApiConfig.Builder()
            .setBaseUrl(GeneralUrl.BASE_URL)
            .setInvalidToken(10)
            .setSucceedCode(0)
            .setTokenInvalidFilter("com.jd.lie.control.quitAppBroadcastFilter")
            .setHeads(headMap)
            .build()
        build.init(this)
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)

    }
}