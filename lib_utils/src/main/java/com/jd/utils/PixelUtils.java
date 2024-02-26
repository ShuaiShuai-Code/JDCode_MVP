package com.jd.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author ：王文彬 on 2020-01-16 16：59
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class PixelUtils {
  /**
   * dp转换成px
   */
  public static int dp2px(Context context, float dpValue) {
    float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  /**
   * px转换成dp
   */
  public static int px2dp(Context context, float pxValue) {
    float scale = context.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  /**
   * sp转换成px
   */
  public static int sp2px(Context context, float spValue) {
    float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
    return (int) (spValue * fontScale + 0.5f);
  }

  /**
   * px转换成sp
   */
  public static int px2sp(Context context, float pxValue) {
    float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
    return (int) (pxValue / fontScale + 0.5f);
  }

  /**
   * 获取屏内容
   */
  public static DisplayMetrics getScreen(Context context) {
    return context.getResources().getDisplayMetrics();
  }
}
