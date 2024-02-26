package com.jd.lie.mine.ui.activity

import android.content.res.Configuration
import android.content.res.Resources
import android.widget.TextView
import com.jd.common.constant.Constant
import com.jd.common.ui.activity.BaseActivity
import com.jd.lie.mine.R
import com.jd.lie.mine.utils.FontSizeView.OnChangeCallbackListener
import com.jd.utils.AppManagerUtils
import com.jd.utils.LogUtils
import com.jd.utils.SpUtils
import com.jd.utils.sb.thridlib.SizeUtils
import kotlinx.android.synthetic.main.activity_typeface_set.*


/**
 * 设置
 */
class TypefaceSetActivity : BaseActivity() {
    override fun initLayout(): Int = R.layout.activity_typeface_set


    private var isChange //用于监听字体大小是否有改动
            = false
    private var defaultPos = 0
    private var vSize: Float = 1f
    override fun needHeader(): Boolean {
        return true
    }

    override fun chooseHeader(): Header {
        return Header.SUB
    }

    override fun initListener() {
        super.initListener()
        fsvFontSize.setChangeCallbackListener(OnChangeCallbackListener { position ->
            val dimension = resources.getDimensionPixelSize(R.dimen.sp_16sp)
            //根据position 获取字体倍数
            textSizeMagnification = (position).toFloat()//滑动位置
            LogUtils.w("滑动位置position=" + position)
            //放大后的sp单位
            vSize = ((0.875 + 0.125 * position).toFloat())//修改当前字体dimension放大倍数
            //改变当前页面大小
            val v: Float = vSize * SizeUtils.px2sp(dimension.toFloat())
            changeTextSize(v)
            LogUtils.w("放大倍数1=" + vSize)

            if (vSize > 0f) {
                if (position > 0)
                    SpUtils.put(this, Constant.SP_position, position)
                SpUtils.put(this, Constant.SP_FontScale, vSize)
                LogUtils.w("fontSizeScale--put" + vSize)
            }
        })
        //注意： 写在改变监听下面 —— 否则初始字体不会改变
        fsvFontSize.setDefaultPosition(defaultPos)
        val sp_position =
            SpUtils.get(this, Constant.SP_position, 1) as Int
        fsvFontSize.setDefaultPosition(sp_position)

    }

    override fun getTvSubLeftTitleView(): TextView {
        return super.getTvSubLeftTitleView()
    }

    private fun changeTextSize(toInt: Float) {
        tv_set.setTextSize(toInt)
        tv_set.textSize
    }

    override fun getResources(): Resources {
        val res = super.getResources()
        val config: Configuration = res.configuration
        config.fontScale = vSize  //1 设置正常字体大小的倍数
        res.updateConfiguration(config, res.displayMetrics)
        LogUtils.w("vSize$vSize")
        return res
    }

    override fun initView() {
        super.initView()
        setTvSubLeftTitle("字体设置")
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManagerUtils.getInstance().finishAllActivity()
    }
}


