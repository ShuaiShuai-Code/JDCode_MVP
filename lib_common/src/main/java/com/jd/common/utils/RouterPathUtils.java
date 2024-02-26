package com.jd.common.utils;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 路由path集合
 */

public class RouterPathUtils {

  /**
   * 主页面
   */
  public static final String HOME = "/home/home";

  /**
   * 登录
   */
  public static final String LOGIN = "/login/login";

  /**
   * 控制台
   */
  public static final String CONTROL = "/control/control";

  /**
   * 控制台
   */
  public static final String CONTROL_PANNEL = "/control/pannel ";

  /**
   * 我的
   */
  public static final String MINE = "/mine/mine";

  /**
   * 猜数字 图表页
   */
  public static final String GUESS_NUM_CHART = "/guess_num/chart";

  /**
   * 猜名字 图表页
   */
  public static final String GUESS_NAME_CHART = "/guess_name/chart";

  /**
   * cit 主界面
   */
  public static final String CIT_MAIN = "/cit/main";

  /**
   * 扫码 主界面
   */
  public static final String QR_CODE = "/qrcode/qrcode";


  public static void gotoActivity(String path, AppCompatActivity activity, boolean isFinish) {
    ARouter.getInstance().build(path).navigation();
    if (isFinish) activity.finish();
  }

  public static void gotoActivity(String path, AppCompatActivity activity, Bundle bundle, boolean isFinish) {
    ARouter.getInstance().build(path).with(bundle).navigation();
    if (isFinish) activity.finish();
  }


}
