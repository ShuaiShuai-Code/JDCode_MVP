package com.jd.common.ui.customview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;


/**
 * @author ：王文彬 on 2020/3/16 16：05
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class NoVerticalScrollLinearLayoutManager extends LinearLayoutManager {

  private boolean isScrollEnabled = true;

  public NoVerticalScrollLinearLayoutManager(Context context) {
    super(context);
  }

  public NoVerticalScrollLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
    super(context, orientation, reverseLayout);
  }

  public NoVerticalScrollLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }


  public void setScrollEnabled(boolean flag) {
    this.isScrollEnabled = flag;
  }

  /**
   * 禁止滑动
   * canScrollHorizontally（禁止横向滑动）
   */
  @Override
  public boolean canScrollHorizontally() {
    return isScrollEnabled && super.canScrollVertically();
  }

  /**
   * 禁止滑动
   * canScrollVertically（禁止竖向滑动）
   */
  @Override
  public boolean canScrollVertically() {
    return isScrollEnabled && super.canScrollVertically();
  }
}
