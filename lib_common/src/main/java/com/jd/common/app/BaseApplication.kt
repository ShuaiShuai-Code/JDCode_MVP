package com.jd.common.app

import android.app.Application
import android.util.ArrayMap
import com.alibaba.android.arouter.launcher.ARouter
import com.jd.common.BuildConfig
import com.jd.common.constant.Constant
import com.jd.common.constant.GeneralUrl
import com.jd.utils.LogUtils
import com.jd.utils.LogUtils.d
import com.jd.utils.LogUtils.w
import com.jd.utils.SpUtils
import com.jd.utils.UtilsConfig
import com.mp5a5.www.library.utils.ApiConfig

/**
 * @describe ：
 * @author ：王文彬 on 2020/5/11 18：24
 * @email ：wangwenbin29@jd.com
 */
open class BaseApplication : Application() {

    private val TOKEN =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTA3NDM2NjA1MDIsInBheWxvYWQiOiJ7XCJ0b2tlbklkXCI6XCIyMDIwMDUyNzE1MTQyMDUwMjE0NVwiLFwidXNlcklkXCI6XCIyMDIwMDUyNzE1MTQyMDQ5NzEzOFwiLFwib3BlcmF0ZUlkXCI6bnVsbH0ifQ.2RUAqL4ibtTC6oymJWl0pK9uYTwXHXB_uqT_y5as59c"


    override fun onCreate() {
        super.onCreate()
        UtilsConfig.init(this)
        //initMockToken()
        initNet()
        initARouter()
    }

    private fun initNet() {
        val headMap = ArrayMap<String, String>()
        headMap["Content-Type"] = "application/x-www-form-urlencoded"
        headMap["token"] ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTQ2MTA4MTEyMDAsInBheWxvYWQiOiJ7XCJ0b2tlbklkXCI6XCIyMDIwMDYxMzExMjY1MTIwMDEyMVwiLFwidXNlcklkXCI6bnVsbCxcIm9wZXJhdGVJZFwiOlwiMjAyMDA1MTIxNjQ4MzQ5MzIxOTNcIn0ifQ.t6f8socSEmWv2VyAk0d-bl4c4fm5T6ZyRzfZVBagyn8"
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