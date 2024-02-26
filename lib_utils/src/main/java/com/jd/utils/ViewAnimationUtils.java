package com.jd.utils;

/**
 * @author ：王文彬 on 2020/3/27 19：01
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */

import android.content.Context;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import androidx.annotation.AnimRes;

/**
 * 动画工具
 * 注意，切换方法overridePendingTransition只能在startActivity和finish方法之后调用。
 *
 * 第一个参数为第一个Activity离开时的动画，第二参数为所进入的Activity的动画效果
 * 淡入淡出效果
 * overridePendingTransition(R.anim.fade, R.anim.hold);
 * 放大淡出效果
 * overridePendingTransition(R.anim.my_scale_action,R.anim.my_alpha_action);
 * 转动淡出效果
 * overridePendingTransition(R.anim.scale_rotate,R.anim.my_alpha_action);
 * 转动淡出效果
 * overridePendingTransition(R.anim.scale_translate_rotate,R.anim.my_alpha_action);
 * 左上角展开淡出效果
 * overridePendingTransition(R.anim.scale_translate,R.anim.my_alpha_action);
 * 压缩变小淡出效果
 * overridePendingTransition(R.anim.hyperspace_in,R.anim.hyperspace_out);
 * 右往左推出效果
 * overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
 * 下往上推出效果
 * overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
 * 左右交错效果
 * overridePendingTransition(R.anim.slide_left,R.anim.slide_right);
 * 放大淡出效果
 * overridePendingTransition(R.anim.wave_scale,R.anim.my_alpha_action);
 * 缩小效果
 * overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
 * 上下交错效果
 * overridePendingTransition(R.anim.slide_up_in,R.anim.slide_down_out);
 */
public class ViewAnimationUtils {

  /**
   * @param context 上下文
   * @param res     资源动画
   * @return Animation
   */
  public static Animation setAnimationRes(Context context, @AnimRes int res) {
    return AnimationUtils.loadAnimation(context, res);
  }

  /**
   * TranslateAnimation
   * 画面转换位置移动动画效果
   */
  public static TranslateAnimation getTrainsAnimation(Context context) {
    TranslateAnimation animation = new TranslateAnimation(context, null);
    animation.setFillAfter(true);
    return animation;
  }

  /**
   * TranslateAnimation
   * 画面转换位置移动动画效果
   *
   * @param fromXDelta 动画起始时 X坐标上的移动位置
   * @param toXDelta   动画结束时 X坐标上的移动位置
   * @param fromYDelta 动画起始时Y坐标上的移动位置
   * @param toYDelta   动画结束时Y坐标上的移动位置
   */
  public static TranslateAnimation getTrainsAnimation(float fromXDelta, float toXDelta, float fromYDelta,
                                                      float toYDelta) {
    TranslateAnimation animation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
    animation.setFillAfter(true);
    return animation;
  }

  /**
   * AlphaAnimation
   * 渐变透明度动画效果
   */
  public static AlphaAnimation getAlphaAnimation(Context context) {
    AlphaAnimation animation = new AlphaAnimation(context, null);
    animation.setFillAfter(true);
    return animation;
  }

  /**
   * AlphaAnimation
   * 渐变透明度动画效果
   *
   * @param fromAlpha 动画开始时候透明度
   * @param toAlpha   动画结束时候透明度
   */
  public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha) {
    AlphaAnimation animation = new AlphaAnimation(fromAlpha, toAlpha);
    animation.setFillAfter(true);
    return animation;
  }

  /**
   * ScaleAnimation
   * 渐变尺寸伸缩动画效果
   */
  public static ScaleAnimation getScaleAnimation(Context context) {
    ScaleAnimation animation = new ScaleAnimation(context, null);
    animation.setFillAfter(true);
    return animation;
  }

  /**
   * ScaleAnimation
   * 渐变尺寸伸缩动画效果
   *
   * @param fromX 动画起始时 X坐标上的伸缩尺寸
   * @param toX   动画结束时 X坐标上的伸缩尺寸
   * @param fromY 动画起始时Y坐标上的伸缩尺寸
   */
  public static ScaleAnimation getScaleAnimation(float fromX, float toX, float fromY, float toY) {
    ScaleAnimation animation = new ScaleAnimation(fromX, toX, fromY, toY);
    animation.setFillAfter(true);
    return animation;
  }

  /**
   * ScaleAnimation
   * 渐变尺寸伸缩动画效果
   *
   * @param fromX  动画起始时 X坐标上的伸缩尺寸
   * @param toX    动画结束时 X坐标上的伸缩尺寸
   * @param fromY  动画起始时Y坐标上的伸缩尺寸
   * @param pivotX 动画在X轴相对于物件位置类型
   * @param pivotY 动画相对于物件的X坐标的开始位置
   */
  public static ScaleAnimation getScaleAnimation(float fromX, float toX, float fromY, float toY, float pivotX,
                                                 float pivotY) {
    ScaleAnimation animation = new ScaleAnimation(fromX, toX, fromY, toY, pivotX, pivotY);
    animation.setFillAfter(true);
    return animation;
  }

  /**
   * RotateAnimation
   * 画面转移旋转动画效果
   */
  public static RotateAnimation getRotateAnimation(Context context) {
    RotateAnimation animation = new RotateAnimation(context, null);
    animation.setFillAfter(true);
    return animation;
  }

  /**
   * RotateAnimation
   * 画面转移旋转动画效果
   *
   * @param fromDegrees 动画起始时的旋转角度
   * @param toDegrees   动画旋转到的角度
   */
  public static RotateAnimation getRotateAnimation(float fromDegrees, float toDegrees) {
    RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees);
    animation.setFillAfter(true);
    return animation;
  }

  /**
   * RotateAnimation
   * 画面转移旋转动画效果
   *
   * @param fromDegrees 动画起始时的旋转角度
   * @param toDegrees   动画旋转到的角度
   * @param pivotX      动画在X轴相对于物件位置类型
   * @param pivotY      动画相对于物件的X坐标的开始位置
   */
  public static RotateAnimation getRotateAnimation(float fromDegrees, float toDegrees, float pivotX, float pivotY) {
    RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY);
    animation.setFillAfter(true);
    return animation;
  }

  /**
   * RotateAnimation
   * 画面转移旋转动画效果
   *
   * @param fromDegrees 动画起始时的旋转角度
   * @param toDegrees   动画旋转到的角度
   * @param pivotXType  动画在X轴相对于物件位置类型
   * @param pivotXValue 动画相对于物件的X坐标的开始位置
   * @param pivotYType  动画在Y轴相对于物件位置类型
   * @param pivotYValue 动画相对于物件的Y坐标的开始位置
   */
  public static RotateAnimation getRotaAnimation(float fromDegrees, float toDegrees, int pivotXType,
                                                 float pivotXValue, int pivotYType, float pivotYValue) {
    RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType,
        pivotYValue);
    animation.setFillAfter(true);
    return animation;
  }

  /**
   * 组合动画
   */
  public static AnimationSet getAnimationSet(Context context) {
    AnimationSet animation = new AnimationSet(context, null);
    animation.setFillAfter(true);
    return animation;
  }

  /**
   * 增加动画
   */
  public static void addAnimation(AnimationSet set, Animation animation) {
    set.addAnimation(animation);
  }

  /**
   * 设置动画运行的时间
   */
  public static void setAnimationDuration(Animation animation, long time) {
    animation.setDuration(time);
  }

  /**
   * 设置动画停顿时间
   */
  public static void setAnimationStartTime(Animation animation, long time) {
    animation.setStartTime(time);
  }

  /**
   * 动画的插入器
   *
   * @param factor accelerate_decelerate_interpolator   加速-减速 动画插入器
   *               accelerate_interpolator               加速-动画插入器
   *               decelerate_interpolator               减速- 动画插入器
   */
  public static void setAnimationInterpolator(Animation animation, int factor) {
    animation.setInterpolator(new AccelerateInterpolator(factor));
  }
}
