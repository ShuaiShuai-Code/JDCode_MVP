package com.jd.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * @author ：王文彬 on 2020/3/25 16：50
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class FrameAnimationUtils {

  public static AnimationDrawable getAnimationDrawable(@NonNull ImageView view, @DrawableRes int res) {
    view.setImageResource(res);
    return (AnimationDrawable) view.getDrawable();
  }

  public static AnimationDrawable getAnimationDrawable(Context context, @NonNull ImageView view, @DrawableRes int res) {
    view.setBackground(ContextCompat.getDrawable(context, res));
    return (AnimationDrawable) view.getBackground();
  }

}
