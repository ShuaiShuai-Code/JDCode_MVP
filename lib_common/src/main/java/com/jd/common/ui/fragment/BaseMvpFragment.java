package com.jd.common.ui.fragment;


import com.jd.common.mvp.BasePresenter;
import com.jd.common.mvp.BaseView;

/**
 * @author ：王文彬 on 2020-02-05 11：42
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
@SuppressWarnings("ALL")
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView {

  protected P presenter;

  @Override
  protected void initView() {
    super.initView();
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
  }

  protected abstract P getBasePresenter();

}
