package com.jd.common.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.jd.common.R;
import com.jd.utils.LogUtils;


/**
 * @author ：王文彬 on 2020/3/19 11：23
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class TrialCitDisplayResultCircleView extends View {

  private Paint mPaint;
  private RectF mRectF;

  private int mColorCircle;

  public TrialCitDisplayResultCircleView(Context context) {
    super(context);
    initPaint(context);
  }

  public TrialCitDisplayResultCircleView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    TypedArray typedArray = getContext().obtainStyledAttributes(attrs,
            R.styleable.TrialCitDisplayResultCircleView);
    //获取颜色属性设置的值
    mColorCircle = typedArray.getColor(R.styleable.TrialCitDisplayResultCircleView_colorCircle, Color.GREEN);
    typedArray.recycle();
    initPaint(context);
  }

  public TrialCitDisplayResultCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initPaint(context);
  }

  public TrialCitDisplayResultCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
                                         int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    initPaint(context);
  }


  private void initPaint(Context context) {
    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mRectF = new RectF();
  }


  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //彩色比例颜色
    //mPaint.setColor(Color.parseColor("#FF9800"));
    LogUtils.e("--> mcolorCircle" + mColorCircle);
    mPaint.setColor(mColorCircle);

    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(4f);
    int with = getWidth();
    float radius = with / 2 - 2;
    canvas.drawCircle(with / 2, with / 2, radius, mPaint);
    //打底灰色颜色
    //mPaint.setColor(Color.parseColor("#E5E8F4"));
    //mPaint.setColor(mColorCircle);

//    mRectF.set(with / 2 - radius, with / 2 - radius, with / 2 + radius, with / 2 + radius);
//    canvas.drawArc(mRectF, 270, 90, false, mPaint);
  }


  /**
   * 设置颜色
   */
  public void setCircleColor(int changeColor) {
    LogUtils.e("--> set color" + changeColor);
    mColorCircle = changeColor;
    this.mPaint.setColor(changeColor);
    invalidate();
  }


}
