package com.jd.common.entity;


import java.util.Comparator;
import java.util.List;

/**
 * 审讯端向机器人端发送数据的bean  list
 */
public class NumberBean {


  private String message;
  private String groupOrder;
  private String code;
  private List<ResultBean> result;

  public String getGroupOrder() {
    return groupOrder;
  }

  public void setGroupOrder(String groupOrder) {
    this.groupOrder = groupOrder;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public List<ResultBean> getResult() {
    return result;
  }

  public void setResult(List<ResultBean> result) {
    this.result = result;
  }

  public static class ResultBean implements Comparator<ResultBean> {

    private int num;

    private double value;

    public int getNum() {
      return num;
    }

    public void setNum(int num) {
      this.num = num;
    }

    public double getValue() {
      return value;
    }

    public void setValue(double value) {
      this.value = value;
    }

    @Override
    public int compare(ResultBean o1, ResultBean o2) {
      //return o1.getAge() - o2.getAge();//升序
      return o2.getNum() - o1.getNum();//降序
      //return o2.getNum();//降序
      /*Double d1 = o1.getValue();
      Double d2 =o2.getValue();
      int ret = d2.compareTo(d1);*/
      //return ret;

    }
  }
}
