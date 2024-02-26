package com.jd.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * @describe ：
 * @author ：王文彬 on 2020/3/26 20：13
 * @email ：wangwenbin29@jd.com
 */
class KeyboardUtils {
  
  companion object {
    
    /**
     * 显示软键盘
     */
    fun showKeyboard(view: View) {
      val imm = view.context
          .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      view.requestFocus()
      imm.showSoftInput(view, 0)
    }
    
    /**
     * 隐藏软键盘
     */
    fun hideKeyboard(view: View) {
      val imm = view.context
          .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    
    /**
     * 切换软键盘
     */
    fun toggleSoftInput(view: View) {
      val imm = view.context
          .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.toggleSoftInput(0, 0)
    }
    
  }
  
}