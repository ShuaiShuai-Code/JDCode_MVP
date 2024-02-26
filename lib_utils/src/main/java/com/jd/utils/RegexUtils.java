package com.jd.utils;

import java.util.regex.Pattern;

/**
 * @author ：王文彬 on 2020-02-04 19：17
 * @describe ：正则表达式
 * @email ：wangwenbin29@jd.com
 */
public class RegexUtils {

  /**
   * 数字和英文
   */
  public static final String NUM_AND_ENGLISH = "^[A-Za-z0-9]{4,40}$";
  /**
   * 26个英文字母
   */
  public static final String ALL_ENGLISH = "^[A-Za-z]+$";

  public static final String NUM = "^[0-9]*$";

  public static final String NUMBER_PATTERN = "([+\\\\-]?[0-9]*[.]?[\\\\d]*)";


  public static boolean checkRegex(String patten, String str) {
    return Pattern.matches(patten, str);
  }


}
