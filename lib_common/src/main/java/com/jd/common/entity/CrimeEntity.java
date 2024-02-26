package com.jd.common.entity;

import java.util.List;

/**
 * @author ：王文彬 on 2020/3/16 22：53
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class CrimeEntity {

  public List<Crime> crime;

  public static class Crime {
    public String crimeLevelOne;
    public String crimeLevelOneId;
    public List<Level> level;

    public static class Level {
      public String crimeLevelTwo;
      public String crimeLevelTwoId;
    }
  }


}
