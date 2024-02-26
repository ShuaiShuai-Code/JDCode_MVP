package com.jd.utils;

import java.util.Random;

/**
 * @author ：王文彬 on 2020/3/2 18：18
 * @describe ：数字转换工具类
 * @email ：wangwenbin29@jd.com
 */
public class NumberUtils {

  public static int indexOfAppearStr(String source, String des) {
    return source.indexOf(des);
  }

  private static char[] cnArr = new char[]{'一', '二', '三', '四', '五', '六', '七', '八', '九'};
  private static char[] chArr = new char[]{'十', '百', '千', '万', '亿'};
  private static String allChineseNum = "零一二三四五六七八九十百千万亿";

  /**
   * 中文数字转阿拉伯数字
   *
   * @param chineseNum 中文数字
   */
  public static int chineseNumToArabicNum(String chineseNum) {
    int result = 0;
    int temp = 1;
    int count = 0;
    for (int i = 0; i < chineseNum.length(); i++) {
      boolean b = true;
      char c = chineseNum.charAt(i);
      for (int j = 0; j < cnArr.length; j++) {
        if (c == cnArr[j]) {
          if (0 != count) {
            result += temp;
            temp = 1;
            count = 0;
          }
          temp = j + 1;
          b = false;
          break;
        }
      }
      if (b) {
        for (int j = 0; j < chArr.length; j++) {
          if (c == chArr[j]) {
            switch (j) {
              case 0:
                temp *= 10;
                break;
              case 1:
                temp *= 100;
                break;
              case 2:
                temp *= 1000;
                break;
              case 3:
                temp *= 10000;
                break;
              case 4:
                temp *= 100000000;
                break;
              default:
                break;
            }
            count++;
          }
        }
      }
      //遍历到最后一个字符
      if (i == chineseNum.length() - 1) {
        result += temp;
      }
    }
    return result;
  }

  /**
   * 阿拉伯数字转中文数字
   *
   * @param intInput 阿拉伯数字
   */
  public static String arabicNumToChineseNum(int intInput) {
    String si = String.valueOf(intInput);
    String sd = "";
    if (si.length() == 1) {
      if (intInput == 0) {
        return sd;
      }
      sd += cnArr[intInput - 1];
      return sd;
    } else if (si.length() == 2) {
      if (si.substring(0, 1).equals("1")) {
        sd += "十";
        if (intInput % 10 == 0) {
          return sd;
        }
      } else {
        sd += (cnArr[intInput / 10 - 1] + "十");
      }
      sd += arabicNumToChineseNum(intInput % 10);
    } else if (si.length() == 3) {
      sd += (cnArr[intInput / 100 - 1] + "百");
      if (String.valueOf(intInput % 100).length() < 2) {
        if (intInput % 100 == 0) {
          return sd;
        }
        sd += "零";
      }
      sd += arabicNumToChineseNum(intInput % 100);
    } else if (si.length() == 4) {
      sd += (cnArr[intInput / 1000 - 1] + "千");
      if (String.valueOf(intInput % 1000).length() < 3) {
        if (intInput % 1000 == 0) {
          return sd;
        }
        sd += "零";
      }
      sd += arabicNumToChineseNum(intInput % 1000);
    } else if (si.length() == 5) {
      sd += (cnArr[intInput / 10000 - 1] + "万");
      if (String.valueOf(intInput % 10000).length() < 4) {
        if (intInput % 10000 == 0) {
          return sd;
        }
        sd += "零";
      }
      sd += arabicNumToChineseNum(intInput % 10000);
    }
    return sd;
  }


  /**
   * 生产随机数
   *
   * @param start 开始位置
   * @param end   结束位置
   */
  public static int newRandom(int start, int end) {
    Random random = new Random();
    return random.nextInt(end - start + 1) + start;
  }


}
