package com.jd.utils;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author hao
 * @version 0.1
 * @time 2018年11月21日09:49:18
 * <h4>这是全局的Activity管理类</h4>
 */

public class AppManagerUtils {


  public static Stack<Activity> getActivityStack() {
    return activityStack;
  }

  private static Stack<Activity> activityStack;
  private static volatile AppManagerUtils instance;

  private AppManagerUtils() {
  }

  /**
   * @author <h4>获取单一实例</h4>
   *
   * 单例模式
   * 单例模式的写法：
   * ① 饿汉
   * ②懒汉
   * ③双重锁机制 jdk1.5以上
   * ④静态内部类
   * ⑤枚举  比如枚举，虽然Effective Java中推荐使用，但是在Android平台上却是不被推荐的 安卓平台
   * Enums often require more than twice as much memory as static constants. You should strictly avoid using enums
   * on Android.
   *
   * 双重检查锁法，不能在jdk1.5之前使用，而在Android平台上使用就比较放心了
   * （一般Android都是jdk1.6以上了，不仅修正了volatile的语义问题，还加入了不少锁优化，使得多线程同步的开销降低不少）。
   *
   * 不管采取何种方案，请时刻牢记单例的三大要点：
   * 线程安全
   * 延迟加载
   * 序列化与反序列化安全
   *
   * **************************本类中使用双重锁*************************
   * “双重检查锁”，顾名思义，就是在getSingleton()方法中，进行两次null检查。
   * 看似多此一举，但实际上却极大提升了并发度，进而提升了性能。为什么可以提高并发度呢？
   * 就像上文说的，在单例中new的情况非常少，绝大多数都是可以并行的读操作。
   * 因此在加锁前多进行一次null检查就可以减少绝大多数的加锁操作，执行效率提高的目的也就达到了。
   */
  public static AppManagerUtils getInstance() {
    if (instance == null) {
      synchronized (AppManagerUtils.class) {
        if (instance == null) {
          instance = new AppManagerUtils();
        }
      }
    }
    return instance;
  }

  /**
   * 添加Activity到堆栈
   */
  public void addActivity(Activity activity) {
    if (activityStack == null) {
      activityStack = new Stack<>();
    }
    activityStack.add(activity);
  }

  /**
   * 获取栈顶Activity（堆栈中最后一个压入的）
   */
  public Activity getTopActivity() {
    return activityStack.lastElement();
  }

  /**
   * 结束栈顶Activity（堆栈中最后一个压入的）
   */
  public void finishTopActivity() {
    Activity activity = activityStack.lastElement();
    finishActivity(activity);
  }

  /**
   * 结束指定类名的Activity
   */
  public void finishActivity(Class<?> cls) {
    Iterator iterator = activityStack.iterator();
    while (iterator.hasNext()) {
      Activity activity = (Activity) iterator.next();
      if (activity.getClass().equals(cls)) {
        iterator.remove();
        activity.finish();
      }
    }
  }

  /**
   * 结束所有Activity
   */
  @SuppressWarnings("WeakerAccess")
  public void finishAllActivity() {
    for (int i = 0, size = activityStack.size(); i < size; i++) {
      if (null != activityStack.get(i)) {
        activityStack.get(i).finish();
      }
    }
    activityStack.clear();
  }

  /**
   * 退出应用程序
   */
  public void appExit() {
    try {
      finishAllActivity();  //结束所有的activity
      System.exit(0); //杀死进程
      android.os.Process.killProcess(android.os.Process.myPid());  //杀死当前进程

    } catch (Exception e) {
    }
  }

  /**
   * 结束指定的Activity
   */
  public void finishActivity(Activity activity) {
    if (activity != null) {
      activityStack.remove(activity);
      activity.finish();
      activity = null;
    }
  }

  /**
   * 得到指定类名的Activity
   */
  public Activity getActivity(Class<?> cls) {
    for (Activity activity : activityStack) {
      if (activity.getClass().equals(cls)) {
        return activity;
      }
    }
    return null;
  }


}
