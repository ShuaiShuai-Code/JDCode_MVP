package com.jd.common.api;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * @author ：王文彬 on 2020-02-24 18：18
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class BaseAPIService {

  /**
   * 公共参数初始化的地方
   */
  protected Map<String, Object> getPublicParams() {
    Map<String, Object> map = new HashMap<>(5);
    //map.put("cusId", SpUtils.getString(ConstantUtils.CUSID));
    return map;
  }

  /**
   * 累加参数的接口
   */
  protected Map<String, Object> putParams(Map<String, Object> map, String key, String value) {
    if (!TextUtils.isEmpty(value)) {
      map.put(key, value);
    }
    return map;
  }
}
