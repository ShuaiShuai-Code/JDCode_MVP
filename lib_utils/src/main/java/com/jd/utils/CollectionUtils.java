package com.jd.utils;

import java.util.List;
import java.util.Map;

/**
 * 作者：王文彬 on 2018/2/3 12：23
 * 邮箱：wwb199055@126.com
 */

public class CollectionUtils {

  public static boolean isEmpty(List<?> list) {
    return list != null && list.isEmpty();
  }

  public static boolean isNull(List<?> list) {
    return list == null;
  }

  public static boolean isNotEmpty(List<?> list) {
    return list != null && !list.isEmpty();
  }

  public static <K, V> boolean isNotEmpty(Map<K, V> map) {
    return map != null && !map.isEmpty();
  }

  /**
   * 这行代码中使用了list.containAll()这个方法，但是这个方法的源码中在对集合中的元素比对时使用了equals()方法，如果list2中的元素没有重写equals()方法时会造成判断失误。
   */
  public static boolean isSame(List<?> list1, List<?> list2) {

    if ((isNull(list1) && !isNull(list2) || !isNull(list1) && isNull(list2))) {
      return false;
    }

    if (isNull(list1) && isNull(list2)) {
      return true;
    }

    if ((isEmpty(list1) && !isEmpty(list2) || !isEmpty(list1) && isEmpty(list2))) {
      return false;
    }
    if (isEmpty(list1) && isEmpty(list2)) {
      return true;
    }

    return list1.size() == list2.size() && list1.containsAll(list2);
  }


  public static <E> void swap(List<E> list, int index1, int index2) {
    //定义第三方变量
    E e = list.get(index1);
    //交换值
    list.set(index1, list.get(index2));
    list.set(index2, e);
  }

}
