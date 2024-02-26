package com.jd.utils;

import android.text.TextUtils;

import java.math.BigDecimal;

import androidx.annotation.NonNull;

/**
 * 作者：王文彬 on 2018/4/13 10：21
 * 邮箱：wwb199055@126.com
 */

public class BigDecimalUtils {


  /**
   * BigDecimal的乘法运算封装
   * 默认返回小数位后2位
   */
  public static <T extends Number> BigDecimal safeMultiply(T b1, T b2) {
    if (null == b1 || null == b2) {
      return BigDecimal.ZERO;
    }
    return BigDecimal.valueOf(b1.doubleValue()).multiply(BigDecimal.valueOf(b2.doubleValue())).setScale(2, BigDecimal
        .ROUND_HALF_UP);
  }


  /**
   * BigDecimal的除法运算（具体的返回多少位可以自己更改）
   * 返回2位小数
   */
  public static <T extends Number> BigDecimal safeDivide(T b1, T b2) {
    return safeDivide(b1, b2, BigDecimal.ZERO);
  }

  /**
   * BigDecimal的除法运算，如果除数或者被除数为0，返回默认值
   * 默认返回小数位后2位
   */
  public static <T extends Number> BigDecimal safeDivide(T b1, T b2, BigDecimal defaultValue) {
    if (null == b1 || null == b2) {
      return defaultValue;
    }
    try {
      return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), 2, BigDecimal
          .ROUND_HALF_UP);
    } catch (Exception e) {
      return defaultValue;
    }
  }


  /**
   * 计算金额方法
   */
  public static BigDecimal safeSubtract(BigDecimal b1, BigDecimal... bn) {
    return safeSubtract(true, b1, bn);
  }

  /**
   * BigDecimal的安全减法运算
   */
  public static BigDecimal safeSubtract(Boolean isZero, BigDecimal b1, BigDecimal... bn) {
    if (null == b1) {
      b1 = BigDecimal.ZERO;
    }
    BigDecimal r = b1;
    if (null != bn) {
      for (BigDecimal b : bn) {
        r = r.subtract((null == b ? BigDecimal.ZERO : b));
      }
    }
    return isZero ? (r.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : r) : r;
  }


  /**
   * BigDecimal的加法运算封装
   */
  public static BigDecimal safeAdd(BigDecimal b1, BigDecimal... bn) {
    if (null == b1) {
      b1 = BigDecimal.ZERO;
    }
    if (null != bn) {
      for (BigDecimal b : bn) {
        b1 = b1.add(null == b ? BigDecimal.ZERO : b);
      }
    }
    return b1;
  }


  public static BigDecimal stringToBigDecimal(String str) {
    return new BigDecimal(str);
  }

  /**
   * 保留n位小数的String
   */
  public static String stringToSaveDecimal(String value, int type, int saveNum) {

    if (StringUtils.isNotNull(value)) {
      BigDecimal decimal = new BigDecimal(value);
      BigDecimal scale = decimal.setScale(saveNum, type);
      return scale.toString();
    } else {
      return null;
    }
  }

  /**
   * 保留两位小数的String并返回"symbol"标识符
   */
  public static String stringToSaveDecimal(@NonNull String value, String symbol) {

    if (!TextUtils.isEmpty(value)) {
      BigDecimal decimal = new BigDecimal(value);
      BigDecimal scale = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
      return scale.toString();
    } else {
      return symbol;
    }
  }


  /**
   * double 保留n位小数
   *
   * @param value   输入值
   * @param saveNum 保留位数
   * @return 输出值
   */
  public static double saveDecimal(double value, int saveNum) {
    BigDecimal decimal = new BigDecimal(value);
    return decimal.setScale(saveNum, BigDecimal.ROUND_HALF_UP).doubleValue();
  }


  /**
   * 小于等于
   *
   * @param num   要比较的数
   * @param opNum 目标数
   */
  public static boolean lessThan(double num, double opNum) {
    BigDecimal nNum = new BigDecimal(num);
    return nNum.compareTo(new BigDecimal(opNum)) < 0;
  }

  /**
   * 小于
   *
   * @param num   要比较的数
   * @param opNum 目标数
   */
  public static boolean lessThanEquals(double num, double opNum) {
    BigDecimal nNum = new BigDecimal(num);
    return nNum.compareTo(new BigDecimal(opNum)) <= 0;
  }


  /**
   * 大于等于
   *
   * @param num   要比较的数
   * @param opNum 目标数
   */
  public static boolean largeThanEquals(double num, double opNum) {
    BigDecimal nNum = new BigDecimal(num);
    return nNum.compareTo(new BigDecimal(opNum)) >= 0;
  }

  /**
   * 大于
   *
   * @param num   要比较的数
   * @param opNum 目标数
   */
  public static boolean largeThan(double num, double opNum) {
    BigDecimal nNum = new BigDecimal(num);
    return nNum.compareTo(new BigDecimal(opNum)) > 0;
  }


}
