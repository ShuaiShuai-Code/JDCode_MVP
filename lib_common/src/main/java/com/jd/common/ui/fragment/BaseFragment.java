package com.jd.common.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jd.common.R;
import com.trello.rxlifecycle3.components.support.RxFragment;

import org.simple.eventbus.EventBus;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author ：王文彬 on 2020-02-05 11：42
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */

public abstract class BaseFragment extends RxFragment {

  private AppCompatActivity mContext = null;
  protected WeakReference<View> v;
  protected View view;
  private TextView tvMiddleTitle;
  private ImageView ivLeftBtn;
  private ImageView ivRightImgBtn;
  private TextView tvRightTxtBtn;
  private TextView tvSubLeftTitle;
  private Unbinder mBind;


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mContext = (AppCompatActivity) getActivity();
    initParams();
  }

  @Nullable
  @Override
  public View onCreateView(
          @NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    mContext = (AppCompatActivity) getActivity();
    if (v == null || v.get() == null) {
      view = inflater.inflate(initLayout(), container, false);
      v = new WeakReference<>(view);
      ButterKnife.bind(this, v.get());
    } else {
      ViewGroup parent = (ViewGroup) v.get().getParent();
      if (parent != null) {
        parent.removeView(v.get());
      }
    }
    ButterKnife.bind(this, view);
    return needHeader() ? getMergerView(view, chooseHeader()) : view;
  }


  private View getMergerView(@NonNull View v, Header header) {
    View rootView = View.inflate(mContext, R.layout.view_base_fragment, null);
    if (header.equals(Header.MAIN)) {
      rootView.findViewById(R.id.rl_main_content).setVisibility(View.VISIBLE);
      tvMiddleTitle = rootView.findViewById(R.id.tv_middle_title);
      ivLeftBtn = rootView.findViewById(R.id.iv_left_img);
      ivRightImgBtn = rootView.findViewById(R.id.iv_right_img);
      tvRightTxtBtn = rootView.findViewById(R.id.tv_right_txt);
      LinearLayout llContainer = rootView.findViewById(R.id.ll_frag_container);
      llContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
          LinearLayout.LayoutParams.MATCH_PARENT));
      leftBtnClickListener();
      rightImgBtnClickListener();
      rightTxtBtnClickListener();
      llContainer.addView(v);
    } else {
      LinearLayout llSubContainer = rootView.findViewById(R.id.ll_sub_container);
      llSubContainer.setVisibility(View.VISIBLE);
      llSubContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
          LinearLayout.LayoutParams.MATCH_PARENT));
      tvSubLeftTitle = rootView.findViewById(R.id.tv_sub_left_title);
      llSubContainer.addView(v);
    }

    return rootView;
  }

  private void leftBtnClickListener() {
    if (ivLeftBtn != null) {
      ivLeftBtn.setOnClickListener(v -> mContext.finish());
    }
  }

  private void rightImgBtnClickListener() {
    if (ivRightImgBtn != null) {
      ivRightImgBtn.setOnClickListener(v -> setOnRightImgBtnClickListener());
    }
  }

  private void rightTxtBtnClickListener() {
    if (tvRightTxtBtn != null) {
      tvRightTxtBtn.setOnClickListener(v -> setOnRightTxtBtnClickListener());
    }
  }

  protected void setOnRightImgBtnClickListener() {

  }

  protected void setOnRightTxtBtnClickListener() {

  }


  public void setTitle(String title) {
    if (tvMiddleTitle != null) {
      tvMiddleTitle.setText(title);
    }
  }


  protected ImageView getLeftBtnView() {
    if (null != ivLeftBtn) {
      return ivLeftBtn;
    }
    return null;
  }

  protected ImageView getRightImgBtnView() {
    if (null != ivRightImgBtn) {
      return ivRightImgBtn;
    }
    return null;
  }

  protected TextView getRightTxtBtnView() {
    if (null != tvRightTxtBtn) {
      return tvRightTxtBtn;
    }
    return null;
  }

  /**
   * 获取次级标题View
   */
  protected TextView getTvSubLeftTitleView() {
    if (null != tvSubLeftTitle) {
      return tvSubLeftTitle;
    }
    return null;
  }

  /**
   * 设置次级标题
   *
   * @param title 标题
   */
  protected void setTvSubLeftTitle(@NonNull String title) {
    if (null != tvSubLeftTitle) {
      tvSubLeftTitle.setText(title);
    }
  }

  /**
   * @return 初始化布局
   */
  protected abstract @LayoutRes
  int initLayout();

  protected boolean needHeader() {
    return false;
  }

  protected Header chooseHeader() {
    return Header.MAIN;
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    EventBus.getDefault().register(this);
    initView();
    initAdapter();
    initNet();
    initListener();
  }

  protected void initParams() {

  }

  protected void initView() {

  }

  protected void initAdapter() {

  }

  protected void initNet() {

  }

  protected void initListener() {
  }

  @Override
  public void onResume() {
    super.onResume();
    mContext = (AppCompatActivity) getActivity();
  }

  public AppCompatActivity getThisActivity() {
    return mContext;
  }

  public BaseFragment getBaseFragment() {
    return this;
  }

  public void gotoActivity(Class<?> clazz) {
    gotoActivity(clazz, false);
  }

  public void gotoActivity(Class<?> clazz, boolean isFinish) {
    mContext.startActivity(new Intent(mContext, clazz));
    if (isFinish) {
      mContext.finish();
    }
  }

  public void gotoActivity(@NonNull Class<?> clazz, @NonNull Bundle bundle, boolean isFinish) {
    Intent intent = new Intent(mContext, clazz);
    intent.putExtras(bundle);
    mContext.startActivity(intent);
    if (isFinish) {
      mContext.finish();
    }
  }


  @Override
  public void onDestroy() {
    super.onDestroy();
    mContext = null;
    EventBus.getDefault().unregister(this);
  }

  public enum Header {
    /**
     * 主标题
     */
    MAIN,
    /**
     * 次标题
     */
    SUB
  }

}
