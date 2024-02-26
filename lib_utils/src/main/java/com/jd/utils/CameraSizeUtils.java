package com.jd.utils;


import android.hardware.Camera;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CameraSizeUtils {
  private static final String tag = "qademo";
  private CameraSizeComparator sizeComparator = new CameraSizeComparator();
  private static CameraSizeUtils myCamPara = null;
  public static float SCREEN_SCALE = 4 / 3;

  private CameraSizeUtils() {

  }

  public static CameraSizeUtils getInstance() {
    if (myCamPara == null) {
      myCamPara = new CameraSizeUtils();
      return myCamPara;
    } else {
      return myCamPara;
    }
  }

  public Camera.Size getPreviewSize(List<Camera.Size> list, int th, float scale) {
    Collections.sort(list, sizeComparator);

    //Log.e(tag,"prepareCameraParaments:scale-"+scale);
    int i = 0;
    for (Camera.Size s : list) {
      if ((s.width > th) && equalRate(s, scale)) {
        Log.i(tag, "最终设置预览尺寸:w = " + s.width + "h = " + s.height);
        break;
      }
      i++;
    }

    if (i >= list.size()) {
      int tempIndex = i / 2;
      return list.get(tempIndex);  //默认第一个最高的，第一个是最高的
    }

    return list.get(i);
  }

  public Camera.Size getPictureSize(List<Camera.Size> list, int th, float scale) {
    Collections.sort(list, sizeComparator);

    int i = 0;
    for (Camera.Size s : list) {
      if ((s.width > th) && equalRate(s, scale)) {
        Log.i(tag, "最终设置图片尺寸:w = " + s.width + "h = " + s.height);
        break;
      }
      i++;
    }


    return list.get(i);
  }

  public boolean equalRate(Camera.Size s, float rate) {
    float r = (float) (s.width) / (float) (s.height);
    if (Math.abs(r - rate) <= 0.2) {
      return true;
    } else {
      return false;
    }
  }

  public class CameraSizeComparator implements Comparator<Camera.Size> {
    //按升序排列
    public int compare(Camera.Size lhs, Camera.Size rhs) {
      // TODO Auto-generated method stub
      if (lhs.width == rhs.width) {
        return 0;
      } else if (lhs.width > rhs.width) {
        return 1;
      } else {
        return -1;
      }
    }

  }
}

