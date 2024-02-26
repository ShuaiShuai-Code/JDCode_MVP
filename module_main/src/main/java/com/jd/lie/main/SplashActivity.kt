package com.jd.lie.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.jd.utils.StatusBarUtils
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

private const val GOTO_LOGIN_UI = 2
private const val GOTO_HOME_UI = 3
private const val START_TIME: Int = 500

class SplashActivity : RxAppCompatActivity() {
  
  private val mHandler by lazy {
    @SuppressLint("HandlerLeak")
    object : Handler() {
      override fun handleMessage(msg: Message?) {
        when (msg!!.what) {
          GOTO_LOGIN_UI -> gotoLoginActivity()
          GOTO_HOME_UI -> gotoHomeActivity()
        }
      }
    }
  }
  
  private fun gotoHomeActivity() {
    gotoActivity<MainActivity>(true)
  }
  
  private fun gotoLoginActivity() {
    gotoActivity<MainActivity>(true)
  }
  
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initStatusBar()
    goWhere()
  }
  
  private fun initStatusBar() {
    StatusBarUtils.translucent(this)
  }
  
  private fun goWhere() {
    val goType = -1
    if (goType == -1) {
      mHandler.sendEmptyMessageDelayed(GOTO_LOGIN_UI, START_TIME.toLong())
    } else {
      mHandler.sendEmptyMessageDelayed(GOTO_HOME_UI, START_TIME.toLong())
    }
  }
  
  override fun onDestroy() {
    super.onDestroy()
    mHandler.removeCallbacksAndMessages(null)
  }
  
  private inline fun <reified T : Activity> Activity.gotoActivity(isFinish: Boolean) {
    startActivity(Intent(this, T::class.java))
    if (isFinish) this.finish()
    
  }
  
  
}
