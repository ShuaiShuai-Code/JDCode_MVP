package com.jd.utils;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ：王文彬 on 2020-02-21 17：23
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class GsonUtil {

  public static final Gson GSON = new GsonBuilder()
      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
      .setDateFormat("yyyy-MM-dd HH:mm:ss")
      .setPrettyPrinting() //自动格式化换行
      .create();


  /**
   * 获取gson解析器
   *
   * @return Gson
   */
  public static Gson getGson() {
    return GSON;
  }


  /**
   * 对象转换为json
   *
   * @param object 对象
   * @return json
   */
  public static String object2Json(Object object) {
    return GSON.toJson(object);
  }


  /**
   * Json转为对象
   *
   * @param json  Json
   * @param clazz Object
   * @param <T>   泛型
   * @return Object
   */
  public static <T> T json2Object(String json, Class<T> clazz) {
    try {
      return GSON.fromJson(json, clazz);
    } catch (JsonSyntaxException e) {
      e.printStackTrace();
      return null;
    }
  }


  /**
   * JSON转换为对象-针对泛型的类型
   *
   * @param json Json
   * @param type 泛型类型
   * @param <T>  泛型
   * @return Object
   */
  public static <T> T json2Object(String json, Type type) {
    return GSON.fromJson(json, type);
  }

  public static <T> List<T> json2List(String json, Type type) {
    return GSON.fromJson(json, type);
  }

  public static <T> List<T> json2List(String json) {
    List<T> tempList = new ArrayList<>();
    if (!TextUtils.isEmpty(json)) {
      Gson gson = new Gson();
      tempList = gson.fromJson(json, new TypeToken<List<T>>() {
      }.getType());
    }
    return tempList;
  }


  public static <T> String list2Json(List<T> list) {
    Gson gson = new Gson();
    return gson.toJson(list);
  }

  /**
   * 对象转Map
   *
   * @param obj Object
   * @return Map
   */
  public static Map<String, Object> object2Map(Object obj) {
    if (obj == null) {
      return null;
    }
    Map<String, Object> map = new HashMap<>(10);
    Field[] declaredFields = obj.getClass().getDeclaredFields();
    try {
      for (Field field : declaredFields) {
        field.setAccessible(true);
        map.put(field.getName(), field.get(obj));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return map;
  }


  /**
   * map转json
   *
   * @param map map
   * @return json
   */
  public static <K, V> String map2Json(Map<K, V> map) {
    Gson gson = new Gson();
    return gson.toJson(map);
  }

  /**
   * json转Map
   *
   * @param json json
   * @return Map
   */
  public static <K, T> Map<K, T> json2Map(String json) {
    Map<K, T> map = new HashMap<>();
    if (json == null) {
      return map;
    }
    Gson gson = new Gson();
    map = gson.fromJson(json, new TypeToken<Map<K, T>>() {
    }.getType());
    return map;
  }


}
