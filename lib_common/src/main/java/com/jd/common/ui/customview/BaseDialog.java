package com.jd.common.ui.customview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.jd.common.R;

import java.util.Objects;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;


/**
 * @author ：王文彬 on 2020/3/10 16：00
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public abstract class BaseDialog extends Dialog {

  private boolean mIsBackCancelable = true;
  private Context mContext;
  private boolean mIsCancelable = true;
  private float widthOffset = 0.9f;

  public BaseDialog(@NonNull Context context) {
    super(context);
    this.mContext = context;
    initView();
  }


  /**
   * 自定义布局及主题的构造方法
   *
   * @param context 上下文
   * @param theme   主题
   */
  public BaseDialog(@NonNull Context context, int theme) {
    super(context, theme);
    this.mContext = context;
    initView();
  }

  public BaseDialog(@NonNull Context context, @FloatRange(from = 0.1, to = 1.0) float widthOffset) {
    super(context);
    this.widthOffset = widthOffset;
    initView();
  }

  public BaseDialog(@NonNull Context context, int theme, Boolean isBackCancelable) {
    super(context, theme);
    this.mContext = context;
    this.mIsBackCancelable = isBackCancelable;
    initView();
  }

  public BaseDialog(@NonNull Context context, int theme, Boolean isBackCancelable, float widthOffset) {
    super(context, theme);
    this.mContext = context;
    this.mIsBackCancelable = isBackCancelable;
    this.widthOffset = widthOffset;
    initView();
  }

  public BaseDialog(Context context, boolean isCancelable, boolean isBackCancelable) {
    super(context, R.style.BaseDialog);
    this.mContext = context;
    this.mIsCancelable = isCancelable;
    this.mIsBackCancelable = isBackCancelable;
    initView();
  }

  private void initView() {
    setContentView(setDialogLayout());
    setCancelable(mIsCancelable);
    setCanceledOnTouchOutside(mIsBackCancelable);

    Window window = this.getWindow();
    WindowManager windowManager = Objects.requireNonNull(window).getWindowManager();
    Display d = windowManager.getDefaultDisplay();
    Objects.requireNonNull(window).setGravity(Gravity.CENTER);
    WindowManager.LayoutParams params = window.getAttributes();
    Point size = new Point();
    d.getSize(size);
    params.width = (int) (size.x * widthOffset);
    //params.width = (int) (d.getWidth() * 0.9);
    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
    window.setAttributes(params);
  }

  protected abstract View setDialogLayout();
}
