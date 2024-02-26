package com.jd.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author ：王文彬 on 2020-02-13 17：09
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class DateUtils {

  public static final String PATTEN_0 = "yyyy-MM-dd HH:mm:ss.S";

  public static final String PATTEN_1 = "yyyy-MM-dd HH:mm:ss";

  public static final String PATTEN_2 = "yyyy-MM-dd HH:mm";

  public static final String PATTEN_3 = "yyyy-MM-dd";

  public static final String PATTEN_4 = "yyyy-MM";

  public static final String PATTEN_5 = "HH:mm:ss.S";

  public static final String PATTEN_6 = "HH:mm:ss";

  public static final String PATTEN_7 = "HH:mm";

  public static final String PATTEN_8 = "yyyy";

  public static final String PATTEN_9 = "MM";

  public static final String PATTEN_10 = "dd";

  public static long getCurrentTimeMillis() {
    return System.currentTimeMillis();
  }

  public static String getPattenTime(long timeMillis, String patten) {
    DateFormat dateFormat = new SimpleDateFormat(patten, Locale.CHINA);
    return dateFormat.format(new Date(timeMillis));
  }

  public static String getCurrentTime(String patten) {
    DateFormat dateFormat = new SimpleDateFormat(patten, Locale.CHINA);
    return dateFormat.format(new Date(getCurrentTimeMillis()));
  }


  public static Date setDateParse(String date, String patten) {
    try {
      return new SimpleDateFormat(patten, Locale.CHINA).parse(date);
    } catch (ParseException e) {
      return null;
    }
  }

  /**
   * 判断当前日期是否是当天
   */
  public static boolean isToday(long timeMillis) {
    return android.text.format.DateUtils.isToday(timeMillis);
  }

  public static boolean isSameDay(Calendar vDate1, Calendar vDate2) {
    return vDate1.get(Calendar.YEAR) == vDate2.get(Calendar.YEAR)
        && vDate1.get(Calendar.MONTH) == vDate2.get(Calendar.MONTH)
        && vDate1.get(Calendar.DAY_OF_MONTH) == vDate2
        .get(Calendar.DAY_OF_MONTH);
  }


  public static int compareDate(String dateOne, String dateTwo, String patten) {
    DateFormat dateFormat = new SimpleDateFormat(patten, Locale.CHINA);
    try {
      Date dt1 = dateFormat.parse(dateOne);
      Date dt2 = dateFormat.parse(dateTwo);
      return Long.compare(dt1.getTime(), dt2.getTime());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return 0;

  }


  public static Calendar getCalendar(String date, String patten) {
    Calendar mCalendar = Calendar.getInstance();
    try {
      DateFormat dateFormat = new SimpleDateFormat(patten, Locale.CHINA);
      Date d = dateFormat.parse(date);
      mCalendar.setTime(d);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return mCalendar;
  }

  public static String calendarFormatTime(Calendar calendar, String patten) {
    DateFormat dateFormat = new SimpleDateFormat(patten, Locale.CHINA);
    return dateFormat.format(calendar.getTime());
  }

  /**
   * 日期转字符串
   *
   * @param dateDate Date
   * @param patten   格式化
   */
  public static String date2Str(Date dateDate, String patten) {
    DateFormat formatter = new SimpleDateFormat(patten, Locale.CHINA);
    return formatter.format(dateDate);
  }

  /**
   * 字符串转日期
   *
   * @param strDate Date
   * @param format  格式化
   */
  public static Date str2Date(String strDate, String format) {
    DateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
    ParsePosition pos = new ParsePosition(0);
    return formatter.parse(strDate, pos);
  }
}
