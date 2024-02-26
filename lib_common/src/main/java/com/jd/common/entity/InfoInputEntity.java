package com.jd.common.entity;

import java.io.Serializable;

/**
 * @author ：王文彬 on 2020-02-25 17：50
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class InfoInputEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public String useName;
  public String birthday;
  public String gender;


  public InfoInputEntity() {

  }

  public InfoInputEntity(String useName, String birthday, String gender) {
    this.useName = useName;
    this.birthday = birthday;
    this.gender = gender;
  }

  @Override
  public String toString() {
    return "InfoInputEntity{" +
        "useName='" + useName + '\'' +
        ", birthday='" + birthday + '\'' +
        ", gender='" + gender + '\'' +
        '}';
  }
}
