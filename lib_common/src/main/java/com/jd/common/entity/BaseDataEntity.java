package com.jd.common.entity;

import com.google.gson.annotations.SerializedName;

import com.mp5a5.www.library.net.revert.BaseResponseEntity;
import com.mp5a5.www.library.utils.ApiConfig;

import org.jetbrains.annotations.NotNull;


/**
 * @author ：王文彬 on 2020-02-24 18：50
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class BaseDataEntity<T> extends BaseResponseEntity {

  @SerializedName("resCode")
  private int code;

  @SerializedName("resInfo")
  private String msg;

  public String resMessage;

  public String token;


  @Override
  public boolean success() {
    return ApiConfig.getSucceedCode() == code;
  }

  @Override
  public boolean tokenInvalid() {
    return ApiConfig.getInvalidToken() == code;
  }

  @NotNull
  @Override
  public String getMsg() {
    return msg;
  }

  @Override
  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T resData;


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
