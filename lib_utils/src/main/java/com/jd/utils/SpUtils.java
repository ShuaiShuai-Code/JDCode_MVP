package com.jd.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ：王文彬 on 2020-02-05 11：17
 * @describe ：SharedPreferences工具类
 * @email ：wangwenbin29@jd.com
 */
public class SpUtils {

  public static final String SP_XML = "info_cache";

  public static SharedPreferences getSharedPreferences() {
    return UtilsConfig.getContext().getSharedPreferences(SP_XML,
        Context.MODE_PRIVATE);
  }

  public static String getString(String key) {
    return getSharedPreferences().getString(key, "");
  }

  public static void setString(String key, String value) {
    getSharedPreferences().edit().putString(key, value).apply();
  }

  public static boolean getBoolean(String key) {
    return getSharedPreferences().getBoolean(key, false);
  }

  public static void setBoolean(String key, boolean value) {
    getSharedPreferences().edit().putBoolean(key, value).apply();
  }

  public static int getInt(String key) {
    return getSharedPreferences().getInt(key, -1);
  }

  public static int getInt(String key, int value) {
    return getSharedPreferences().getInt(key, value);
  }

  public static void setInt(String key, int value) {
    getSharedPreferences().edit().putInt(key, value).apply();
  }

  public static long getLong(String key) {
    return getSharedPreferences().getLong(key, -1);
  }

  public static void setLong(String key, long value) {
    getSharedPreferences().edit().putLong(key, value).apply();
  }

  public static void remove(String key) {
    getSharedPreferences().edit().remove(key).apply();
  }

  public static void removeAll() {
    getSharedPreferences().edit().clear().apply();
  }

  public static <T> void setList(String key, List<T> list) {
    Gson gson = new Gson();
    String toJson = gson.toJson(list);
    setString(key, toJson);
  }

  public static <T> List<T> getList(String key, Type type) {
    List<T> tempList = new ArrayList<>();
    String listJson = getString(key);
    if (!TextUtils.isEmpty(listJson)) {
      Gson gson = new Gson();
      tempList = gson.fromJson(listJson, type);
    }
    return tempList;
  }

  public static final String FILE_NAME = "share_data";
  /**
   * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
   *
   * @param context
   * @param key
   * @param object
   */
  public static void put(Context context, String key, Object object)
  {

    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sp.edit();

    if (object instanceof String)
    {
      editor.putString(key, (String) object);
    }
    else if (object instanceof Integer)
    {
      editor.putInt(key, (Integer) object);
    }
    else if (object instanceof Boolean)
    {
      editor.putBoolean(key, (Boolean) object);
    }
    else if (object instanceof Float)
    {
      editor.putFloat(key, (Float) object);
    }
    else if (object instanceof Long)
    {
      editor.putLong(key, (Long) object);
    }
    else
    {
      editor.putString(key, object.toString());
    }

    SharedPreferencesCompat.apply(editor);
  }
  /**
   * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
   *
   * @param context
   * @param key
   * @param defaultObject
   * @return
   */

  public static Object get(Context context, String key, Object defaultObject)
  {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

    if (defaultObject instanceof String)
    {
      return sp.getString(key, (String) defaultObject);
    }
    else if (defaultObject instanceof Integer)
    {
      return sp.getInt(key, (Integer) defaultObject);
    }
    else if (defaultObject instanceof Boolean)
    {
      return sp.getBoolean(key, (Boolean) defaultObject);
    }
    else if (defaultObject instanceof Float)
    {
      return sp.getFloat(key, (Float) defaultObject);
    }
    else if (defaultObject instanceof Long)
    {
      return sp.getLong(key, (Long) defaultObject);
    }

    return null;
  }

  /**
   * 用于保存集合
   *
   * @param map map数据
   * @return 保存结果
   */
  public static <K, V> void setMap(String key, Map<K, V> map) {
    Gson gson = new Gson();
    String json = gson.toJson(map);
    setString(key, json);
  }

  /**
   * 用于取出集合
   *
   * @return Map
   */
  public static <K, T> Map<K, T> getMap(String key) {
    Map<K, T> map = new HashMap<>();
    String strJson = getString(key);
    if (strJson == null) {
      return map;
    }
    Gson gson = new Gson();
    map = gson.fromJson(strJson, new TypeToken<Map<K, T>>() {
    }.getType());
    return map;
  }


  /**
   * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
   * @author zhy
   *
   */
  private static class SharedPreferencesCompat
  {
    private static final Method sApplyMethod = findApplyMethod();

    /**
     * 反射查找apply的方法
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Method findApplyMethod()
    {
      try
      {
        Class clz = SharedPreferences.Editor.class;
        return clz.getMethod("apply");
      } catch (NoSuchMethodException e)
      {
      }

      return null;
    }

    /**
     * 如果找到则使用apply执行，否则使用commit
     *
     * @param editor
     */
    public static void apply(SharedPreferences.Editor editor)
    {
      try
      {
        if (sApplyMethod != null)
        {
          sApplyMethod.invoke(editor);
          return;
        }
      } catch (IllegalArgumentException e)
      {
      } catch (IllegalAccessException e)
      {
      } catch (InvocationTargetException e)
      {
      }
      editor.commit();
    }
  }


}
