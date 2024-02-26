package com.jd.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * @author ：王文彬 on 2020-02-11 12：32
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class DisplayUtil {


  public static DisplayMetrics getDisplayMetrics(Context context) {
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics displayMetrics = new DisplayMetrics();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
    } else {
      windowManager.getDefaultDisplay().getMetrics(displayMetrics);
    }
    return displayMetrics;
  }

  public static DisplayMetrics getDisplayMetric(Context context) {
    return context.getResources().getDisplayMetrics();
  }

  /**
   * 获取屏幕尺寸
   */
  @SuppressWarnings("deprecation")
  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
  public static Point getScreenSize(Context context) {
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = windowManager.getDefaultDisplay();
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
      return new Point(display.getWidth(), display.getHeight());
    } else {
      Point point = new Point();
      display.getSize(point);
      return point;
    }
  }

}
