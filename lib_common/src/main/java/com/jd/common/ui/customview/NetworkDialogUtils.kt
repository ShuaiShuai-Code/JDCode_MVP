package com.jd.common.ui.customview

import android.content.Context
import android.widget.TextView
import com.jd.common.R
import com.wang.avi.AVLoadingIndicatorView

/**
 * @describe ：
 * @author ：王文彬 on 2020/4/27 15：36
 * @email ：wangwenbin29@jd.com
 */
class NetworkDialogUtils {
  
  companion object {
    
    private var dialog: NetworkDialog? = null
    
    @JvmStatic
    fun show(context: Context) {
      dialog = NetworkDialog(context, 0.6F)
      dialog?.setCancelable(false)
      dialog?.setCanceledOnTouchOutside(false)
      dialog?.show()
      dialog?.getParentView()?.apply {
        findViewById<AVLoadingIndicatorView>(R.id.iv_loading_view).show()
      }
      
    }
    
    @JvmStatic
    fun show(context: Context, msg: String) {
      dialog = NetworkDialog(context, 0.6F)
      dialog?.setCancelable(false)
      dialog?.setCanceledOnTouchOutside(false)
      dialog?.show()
      dialog?.getParentView()?.apply {
        findViewById<TextView>(R.id.tv_loading_msg).text = msg
        findViewById<AVLoadingIndicatorView>(R.id.iv_loading_view).show()
      }
      
    }
    
    @JvmStatic
    fun dismiss() {
      dialog?.dismiss()
      dialog?.getParentView()?.apply {
        findViewById<AVLoadingIndicatorView>(R.id.iv_loading_view).hide()
      }
      dialog = null
    }
  }
}