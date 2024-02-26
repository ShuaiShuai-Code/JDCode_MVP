package com.jd.common.ui.customview

import android.content.Context
import android.view.View
import com.jd.common.R

/**
 * @author ：王文彬 on 2020/3/10 14：55
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
class NetworkDialog : BaseDialog {
  
  private lateinit var view: View
  
  constructor(context: Context) : super(context) {}
  constructor(context: Context, theme: Int) : super(context, theme) {}
  constructor(context: Context, widthOffset: Float) : super(context, widthOffset) {}
  constructor(context: Context, theme: Int, isBackCancelable: Boolean) : super(context, theme, isBackCancelable) {}
  constructor(context: Context, isCancelable: Boolean, isBackCancelable: Boolean) : super(context, isCancelable, isBackCancelable) {}
  constructor(context: Context, theme: Int, isBackCancelable: Boolean, widthOffset: Float) : super(context, theme, isBackCancelable, widthOffset) {}
  
  override fun setDialogLayout(): View {
    view = View.inflate(context, R.layout.dialog_net_work_view, null)
    return view
  }
  
  fun  getParentView() : View = view
  
}