package com.jd.common.entity;

import java.io.Serializable;

public class JumpBean implements Serializable {


  private static final long serialVersionUID = 1L;

  private String targetActivity;
  private String param1;  //区分 VA和普通的问题
  private String param2;  //区分介绍视频的种类
  private String param3;
  private String param4;
  private String param5;

  public String gotoType;

  public boolean isGotoAct = false;

  public String getTargetActivity() {
    return targetActivity;
  }

  public void setTargetActivity(String targetActivity) {
    this.targetActivity = targetActivity;
  }

  public String getParam1() {
    return param1;
  }

  public void setParam1(String param1) {
    this.param1 = param1;
  }

  public String getParam2() {
    return param2;
  }

  public void setParam2(String param2) {
    this.param2 = param2;
  }

  public String getParam3() {
    return param3;
  }

  public void setParam3(String param3) {
    this.param3 = param3;
  }

  public String getParam4() {
    return param4;
  }

  public void setParam4(String param4) {
    this.param4 = param4;
  }

  public String getParam5() {
    return param5;
  }

  public void setParam5(String param5) {
    this.param5 = param5;
  }


}
