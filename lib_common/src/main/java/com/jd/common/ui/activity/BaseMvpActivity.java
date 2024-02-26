package com.jd.common.ui.activity;


import com.jd.common.mvp.BasePresenter;
import com.jd.common.mvp.BaseView;
import com.jd.utils.sb.thridlib.FileIOUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：王文彬 on 2020-02-25 11：37
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
@SuppressWarnings("ALL")
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {


  protected P presenter;

  @Override
  protected void initView() {
    super.initView();
    presenter = getBasePresenter();
    presenter.bind(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    presenter.bind(this);

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (presenter.isUnbind()) {
      presenter.unBind();
    }

  }

  protected abstract P getBasePresenter();

}
