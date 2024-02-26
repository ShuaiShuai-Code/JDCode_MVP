package com.jd.common.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jd.common.mvp.BasePresenter;
import com.jd.common.mvp.BaseView;

import org.simple.eventbus.EventBus;

/**
 * @author ：王文彬 on 2020/3/16 20：09
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
@SuppressWarnings("ALL")
public abstract class TrialCitAddFoilProblemMvpFragment<P extends BasePresenter> extends BottomSheetDialogFragment implements BaseView {

  protected P presenter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EventBus.getDefault().register(this);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    presenter = getBasePresenter();
    presenter.bind(this);
  }

  @Override
  public void onResume() {
    super.onResume();
    presenter.bind(this);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (presenter.isUnbind()) {
      presenter.unBind();
    }
    EventBus.getDefault().unregister(this);
  }

  protected abstract P getBasePresenter();
}
