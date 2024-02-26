package com.jd.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;


public class ViewPagerUtil extends ViewPager {


  private boolean scrollFlag = true;

  public ViewPagerUtil(Context context) {
    super(context);
  }

  public ViewPagerUtil(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * 设置其是否能滑动换页
   *
   * @param scrollFlag false 不能换页， true 可以滑动换页
   */
  public void setScrollFlag(boolean scrollFlag) {
    this.scrollFlag = scrollFlag;
  }


  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return scrollFlag && super.onInterceptTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    return scrollFlag && super.onTouchEvent(ev);

  }


}
