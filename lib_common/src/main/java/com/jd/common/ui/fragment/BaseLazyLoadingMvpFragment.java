package com.jd.common.ui.fragment;


import com.jd.common.mvp.BasePresenter;

/**
 * @author ：王文彬 on 2020-02-05 11：42
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public abstract class BaseLazyLoadingMvpFragment<P extends BasePresenter>  extends BaseMvpFragment<P> {

  private boolean isFirstLoading = false;

  @Override
  protected void initNet() {
    super.initNet();
    isFirstLoading = true;
    if (getUserVisibleHint()) {
      isFirstLoading = false;
      initLoading();
    }
  }

  protected abstract void initLoading();

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    isFirstLoading = false;
  }


  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isFirstLoading && isVisibleToUser) {
      isFirstLoading = false;
      initLoading();
    }
  }
}
