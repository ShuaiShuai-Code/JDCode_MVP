package com.jd.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import androidx.annotation.RequiresPermission;

public class Tools {


  /**
   * 判断字符串是否为空
   * 空返回true  ， 非空返回false
   */
  public static boolean JudgeStrEmpty(String s1) {
    boolean ret = true;
    ret = TextUtils.isEmpty(s1);
    return ret;
  }


  /**
   * 功能：身份证的有效验证
   *
   * @param id 身份证号
   * @return 有效：返回"" 无效：返回String信息
   */
  public static String JudgeIdValid(String id) {
    String errorInfo = "";
    IDCardCheck cc = new IDCardCheck();
    try {
      errorInfo = cc.IDCardValidate(id);
    } catch (Exception e) {
      errorInfo = "请输入合法的身份证号码";
      e.printStackTrace();
    }
    return errorInfo;

  }


  /**
   * 获取系统时间
   */
  public static String getDate() {
    Calendar ca = Calendar.getInstance();
    int year = ca.get(Calendar.YEAR);           // 获取年份
    int month = ca.get(Calendar.MONTH);         // 获取月份
    int day = ca.get(Calendar.DATE);            // 获取日
    int minute = ca.get(Calendar.MINUTE);       // 分
    int hour = ca.get(Calendar.HOUR);           // 小时
    int second = ca.get(Calendar.SECOND);       // 秒

    String date = "" + year + (month + 1) + day + hour + minute + second;
    //Log.d(TAG, "date:" + date);

    return date;
  }

  public static String getCurrentTime() {
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    //SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    String timeStr = df.format(new Date()).toString();
    return timeStr;
  }


  public static String getCurrentTimeWithT() {
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
    //SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    String timeStr = df.format(new Date()).toString();
    return timeStr;
  }


  public static String getCurrentTimeForMultipleChoice() {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    String timeStr = df.format(new Date()).toString();
    return timeStr;
  }


  /**
   * 获取SD卡的路径
   */

  public static String getSDPath() {
    File sdDir = null;
    boolean sdCardExist = Environment.getExternalStorageState()
        .equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
    if (sdCardExist) {
      sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
      return sdDir.toString();
    }

    return null;
  }


  /**
   * 检查是否有前置摄像头
   */
  public static boolean checkFrontCamera() {
    int cameraCount = Camera.getNumberOfCameras();
    Camera.CameraInfo info = new Camera.CameraInfo();
    for (int i = 0; i < cameraCount; i++) {
      Camera.getCameraInfo(i, info);
      if (Camera.CameraInfo.CAMERA_FACING_FRONT == info.facing)
        return true;
    }
    return false;
  }


  /**
   * 返回的index从1开始
   */
  public static String getRadioChoice(RadioGroup radioGroup) {
    if (radioGroup == null) return "";
    int count = radioGroup.getChildCount();
    int selectIndex = -1;
    for (int i = 0; i < count; i++) {
      RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
      if (rb.isChecked()) {
        selectIndex = i;
        break;
      }
    }
    selectIndex++; //索引从1开始计数
    return Integer.toString(selectIndex);
  }

  /**
   * 可以处理15位和18位的身份证号
   * eg  15位：A A A A A A Y Y M M D D N N S
   * 18位：A A A A A A Y Y Y Y M M D D N N N S
   * 无法识别时返回空字符串 ""
   */

  public static String getMonthOfIdCard(String idCard) {
    String month = "";
    int len = idCard.length();
    if (len == 18) {
      month = idCard.substring(10, 12);
    } else if (len == 15) {
      month = idCard.substring(8, 10);
    }
    return month;
  }


  /**
   * 根据视频的分辨率大小调整Surface的大小
   */
  public static void changeVideoSize(MediaPlayer mp, SurfaceView sv) {
    int videoWidth = mp.getVideoWidth();
    int videoHeight = mp.getVideoHeight();

    int cw = sv.getWidth();  //container width
    int ch = sv.getHeight();
    Log.d("Tools.java", "视频的宽" + videoWidth + " 和高：" + videoHeight + "\n" +
        " 画布surface宽：" + cw + "高：" + ch);

    //根据视频尺寸去计算->视频可以在sufaceView中放大的最大倍数。
    float max;
    //默认计算竖屏模式
    max = Math.max((float) videoWidth / (float) cw, (float) videoHeight / (float) ch);


        /*if (getResources().getConfiguration().orientation== ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //竖屏模式下按视频宽度计算放大倍数值
            max = Math.max((float) videoWidth / (float) cw,(float) videoHeight / (float) ch);
        } else{
            //横屏模式下按视频高度计算放大倍数值
            max = Math.max(((float) videoWidth/(float) ch),(float) videoHeight/(float) cw);
        }*/

    //视频宽高分别/最大倍数值 计算出放大后的视频尺寸
    videoWidth = (int) Math.ceil((float) videoWidth / max);
    videoHeight = (int) Math.ceil((float) videoHeight / max);

    Log.d("Tools.java", "changeVideoSize: 视频缩放比例是： " + max + "\n" +
        "缩放后视频的宽" + videoWidth + " 和高：" + videoHeight);


    //无法直接设置视频尺寸，将计算出的视频尺寸设置到surfaceView 让视频自动填充。
    sv.setLayoutParams(new LinearLayout.LayoutParams(videoWidth, videoHeight));
  }


  public static String jfsAuthorization(String aKey, String sKey, String msg) {
    String Signature = "";

    //对于京东云存储 来说，Key 就是 SecretKey，Message 就是 UTF-8 编码的 StringToSign，
    // 计算出的结果 Digest 经过 FaceBase64Utils 编码之后就是 Signature。


    String authorizationStr = "jingdong" + " " + aKey + ":" + Signature;

    return authorizationStr;
  }

  //输入文件夹的路径  ，创建文件夹，如果存在就不会重新创建
  public static void createFileDir(String dirPathStr) {
    File currentDir = new File(dirPathStr);
    if (!currentDir.exists()) {
      currentDir.mkdir();
    }
  }


  /**
   * 完整的文件路径
   */
  public static boolean judgeFileExist(String fullPath) {

    try {
      File f = new File(fullPath);
      if (!f.exists()) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;

  }

  //读取asset中的json数据
  public static String getJson(Context context, String fileName) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      AssetManager assetManager = context.getAssets();
      BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
      String line;
      while ((line = bf.readLine()) != null) {
        stringBuilder.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return stringBuilder.toString();
  }


  @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
  public static String getIPAddress(Context context) {
    NetworkInfo info =
        ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    if (info != null && info.isConnected()) {
      if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
        try {

          for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
            NetworkInterface intf = en.nextElement();
            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
              InetAddress inetAddress = enumIpAddr.nextElement();
              if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                return inetAddress.getHostAddress();
              }
            }
          }
        } catch (SocketException e) {
          e.printStackTrace();
        }

      } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
        return ipAddress;
      }
    } else {
      //当前无网络连接,请在设置中打开网络
    }
    return null;
  }

  @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
  public static String getIpAddress(Context context) {
    ConnectivityManager connectivityManager =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      NetworkCapabilities networkCapabilities =
          connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
      if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return intIP2StringIP(wifiInfo.getIpAddress());
      } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
        try {
          for (Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces(); enumeration.hasMoreElements(); ) {
            NetworkInterface networkInterface = enumeration.nextElement();
            for (Enumeration<InetAddress> addresses = networkInterface.getInetAddresses(); addresses.hasMoreElements(); ) {
              InetAddress inetAddress = addresses.nextElement();
              if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                return inetAddress.getHostAddress();
              }
            }
          }
        } catch (SocketException e) {
          e.printStackTrace();
          return null;
        }
      }
    } else {
      NetworkInfo info =
          ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
      if (info != null && info.isConnected()) {
        if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
          try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
              NetworkInterface intf = en.nextElement();
              for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                InetAddress inetAddress = enumIpAddr.nextElement();
                if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                  return inetAddress.getHostAddress();
                }
              }
            }
          } catch (SocketException e) {
            e.printStackTrace();
            return null;
          }
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
          WifiManager wifiManager =
              (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
          WifiInfo wifiInfo = wifiManager.getConnectionInfo();
          return intIP2StringIP(wifiInfo.getIpAddress());
        }
      }
    }
    return null;
  }


  /**
   * 将得到的int类型的IP转换为String类型
   */
  public static String intIP2StringIP(int ip) {
    return (ip & 0xFF) + "." +
        ((ip >> 8) & 0xFF) + "." +
        ((ip >> 16) & 0xFF) + "." +
        (ip >> 24 & 0xFF);
  }


  public static String getFilterStr(String s) {

    String ret = "";

    //判断是否包含大括号
    int start = 0;
    int end = 0;
    start = s.indexOf('{');
    if (start == -1) {
      //不存在特殊符号 '{'
      return s;
    } else {
      //存在的情况

      end = s.indexOf('}');
      ret = s.substring(0, start) + " _____ " + s.substring(end + 1);

      return ret;
    }

  }


  /**
   * 入参为带花括号的字符串，  需要显示的提示信息
   *
   * @param s    你是不是在{20191014155602002}偷的东西？
   * @param tips 1天中的时间划分，比如早晨，中午等
   */

  public static String replaceStr(String s, String tips) {

    String ret = "";

    //判断是否包含大括号
    int start = 0;
    int end = 0;
    start = s.indexOf('{');
    if (start == -1) {
      //不存在特殊符号 '{'
      return s;
    } else {
      //存在的情况

      end = s.indexOf('}');
      ret = s.substring(0, start) + " ( " + tips + " )  " + s.substring(end + 1);

      return ret;
    }

  }


  /**
   * 获取给定字符串中，被花括号包裹的内容，比如124{2323}344， 返回2323，  其他情况返回空字符串
   * 只取第一次遇到的花括号的内容，比如有两组的话，就不管了
   */
  public static String getBraceContent(String s) {

    String ret = "";

    if (s.indexOf('{') == -1) {
      return ret;
    } else {

      int start = s.indexOf('{');
      int end = s.indexOf('}');
      ret = s.substring(start + 1, end);

    }


    return ret;

  }

  /**
   * 计算拟合后的参数
   */

  public static double[] getLinePara(double[][] points, int mLen) {

    int len = mLen;

    double[] dbRt = new double[2];//结果容器
    double dbXSum = 0;
    for (int i = 0; i < len; i++) {
      dbXSum = dbXSum + points[0][i];
    }
    double dbXAvg = dbXSum / len;//x average

    double dbWHeadVal = 0;
    for (int i = 0; i < len; i++) {
      dbWHeadVal = dbWHeadVal + (points[0][i] - dbXAvg) * points[1][i];
    }
    double dbWDown = 0;
    double dbWDownP = 0;

    dbXSum = 0;
    for (int i = 0; i < len; i++) {
      dbWDownP = dbWDownP + points[0][i] * points[0][i];
      dbXSum = dbXSum + points[0][i];
    }
    dbWDown = dbWDownP - (dbXSum * dbXSum / len);
    double dbW = dbWHeadVal / dbWDown;
    dbRt[0] = dbW;
    double dbBSum = 0;
    for (int i = 0; i < len; i++) {
      dbBSum = dbBSum + (points[1][i] - dbW * points[0][i]);
    }
    double dbB = dbBSum / len;
    dbRt[1] = dbB;
    return dbRt;
  }


  /**
   * Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
   *
   * @param src byte[] data
   * @return hex string
   */
  public static String bytesToHexString(byte[] src) {
    StringBuilder stringBuilder = new StringBuilder("");
    if (src == null || src.length <= 0) {
      return null;
    }
    for (int i = 0; i < src.length; i++) {
      int v = src[i] & 0xFF;
      String hv = Integer.toHexString(v);
      if (hv.length() < 2) {
        stringBuilder.append(0);
      }
      stringBuilder.append(hv);
    }
    return stringBuilder.toString();
  }

  /**
   * Convert hex string to byte[]
   *
   * @param hexString the hex string
   * @return byte[]
   */
  public static byte[] hexStringToBytes(String hexString) {
    if (hexString == null || hexString.equals("")) {
      return null;
    }
    hexString = hexString.toUpperCase();
    int length = hexString.length() / 2;
    char[] hexChars = hexString.toCharArray();
    byte[] d = new byte[length];
    for (int i = 0; i < length; i++) {
      int pos = i * 2;
      d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
    }
    return d;
  }

  /**
   * Convert char to byte
   *
   * @param c char
   * @return byte
   */
  public static byte charToByte(char c) {
    return (byte) "0123456789ABCDEF".indexOf(c);
  }


  //将指定byte数组以16进制的形式打印到控制台
  public static void printHexString(byte[] b) {
    for (int i = 0; i < b.length; i++) {
      String hex = Integer.toHexString(b[i] & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      System.out.print(hex.toUpperCase());
    }

  }

  /**
   * 十六进制转化成10进制
   * 输入为字符串，输出为字符串
   * 输入的例子：ffc50588a003e0
   * 去低五位进行转化
   */

  public static String HexToTen(String hex) {
    String ret = "";
    if (hex == null || "".equals(hex)) {
      return ret;
    }

    int len = hex.length();
    if (len - 5 <= 0) {
      return ret;
    }
    String temp = hex.substring(len - 5);

    ret = temp;

    return ret;
  }


  public static String cutString(String s) {
    String r = "";
    if (s.length() > 18 * 2) {
      int start = s.indexOf("21030000");
      r = s.substring(start, start + 18);
    } else {
      return r;
    }
    return r;

  }

  /**
   * 2103-0000-000cd28d-8f
   * xxxx-xxxx-xxxxxxxx-xx
   * 前4位是数据头
   * 接着4位是0
   * 接着8位是有效数据
   * 尾部2位是校验位
   */
  public static String HexToTenSpecialBle(String hex) {
    String ret = "";
    if (hex == null || "".equals(hex)) {
      return ret;
    }

    int len = hex.length();
    if (len - 18 < 0) {
      return ret;
    }
    String temp = hex.substring(8, 16);

    ret = temp;

    return ret;
  }


  //重命名文件

  /**
   * @param oldFilePath 完整路径包括文件名
   * @param overriding  是否覆盖已有文件
   */
  public static boolean renameFile(String oldFilePath, String newFileName, boolean overriding) {
    File oldfile = new File(oldFilePath);
    if (!oldfile.exists()) {
      return false;
    }
    String newFilepath = oldfile.getParent() + File.separator + newFileName;
    File newFile = new File(newFilepath);
    if (!newFile.exists()) {
      return oldfile.renameTo(newFile);
    } else {
      if (overriding) {
        newFile.delete();
        return oldfile.renameTo(newFile);
      } else {
        return false;
      }
    }
  }


  /**
   * path :  a/b/c/mmm.txt
   */
  public static String getFileNameFromPath(String path) {
    File oldFile = new File(path);
    if (!oldFile.exists()) {
      return "";
    }
    String newFileName = path.substring(oldFile.getParent().length());
    return newFileName;

  }


  /**
   * @param currentOrigin 当前原始数据
   * @param frontEMA      frontRMA是上一时刻EMA
   * @param t             时刻
   */
  public static double getEMA(double currentOrigin, double frontEMA, int t) {
    //currentOrigin 是当前原始数据，frontRMA是上一时刻EMA，t为时刻
    if (t == 0) {
      return currentOrigin;
    } else {
      //double ema = currentOrigin - (0.98 * frontEMA + 0.02 * currentOrigin)/(1-Math.pow(0.98,t));
      //double ema =  (0.98 * frontEMA + 0.02 * currentOrigin)/(1-Math.pow(0.98,t));
      double ema = (0.98 * frontEMA + 0.02 * currentOrigin);
      return ema;
    }
  }

  public static String getShortName(String s) {
    String r = "";
    if (TextUtils.isEmpty(s)) {
      return r;
    } else {
      if (s.length() >= 4) {
        r = s.substring(0, 4);
      } else {
        r = s;
      }
    }
    return r;
  }


  /**
   * 给一个数字，类型是字符串，将其递增1，然后格式化后，返回
   * 比如输入  "1"  返回"00002"
   */
  public static String increaseOne(String num) {

    String STR_FORMAT = "00000";
    Integer addOne = Integer.parseInt(num);
    addOne++;
    DecimalFormat df = new DecimalFormat(STR_FORMAT);
    return df.format(addOne);
  }


}
