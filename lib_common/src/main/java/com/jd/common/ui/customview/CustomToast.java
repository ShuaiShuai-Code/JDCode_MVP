package com.jd.common.ui.customview;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jd.common.R;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author ：王文彬 on 2020/3/24 12：18
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class CustomToast {

  public static void show(Context context) {
    show(context, -1, null);
  }

  public static void show(Context context, @DrawableRes int resId) {
    show(context, resId, null);
  }

  public static void show(Context context, @NonNull String txt) {
    show(context, -1, txt);
  }

  /**
   * @param context 上下文
   * @param resId   资源id 如果输入为-1 则使用默认图标
   * @param txt     显示的文字，如果不传入则使用默认文字
   */
  public static void show(Context context, @DrawableRes int resId, @Nullable String txt) {
    View view = View.inflate(context, R.layout.view_custom_toast, null);
    ImageView image = view.findViewById(R.id.iv_toast_img);
    if (resId != -1) {
      image.setImageResource(resId);
    }
    TextView text = view.findViewById(R.id.tv_toast_txt);
    if (txt != null) {
      text.setText(txt);
    }
    Toast toast = new Toast(context);
    toast.setGravity(Gravity.CENTER, 0, 0);
    toast.setDuration(Toast.LENGTH_SHORT);
    toast.setView(view);
    toast.show();
  }


}
