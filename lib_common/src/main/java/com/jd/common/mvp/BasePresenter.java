package com.jd.common.mvp;

import java.lang.ref.WeakReference;

/**
 * @author ：王文彬 on 2020-02-25 11：37
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public abstract class BasePresenter<V extends BaseView> {
  protected V v;
  protected WeakReference<V> weakReference;


  public void bind(V view) {
    this.v = view;
    weakReference = new WeakReference<V>(v);
  }

  public void unBind() {
    if (weakReference != null) {
      weakReference.clear();
      weakReference = null;
    }
  }

  public boolean isUnbind() {
    return weakReference != null && weakReference.get() != null;
  }

  public V getView() {
    if (weakReference != null) {
      return weakReference.get();
    }

    return null;
  }
}
