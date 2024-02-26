package com.jd.lie.mine.ui.fragment;

import android.content.Intent;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jd.common.ui.fragment.BaseFragment;
import com.jd.common.ui.fragment.BaseMvpFragment;
import com.jd.lie.mine.R;
import com.jd.lie.mine.network.entity.UserEntity;
import com.jd.lie.mine.presenter.LoginContract;
import com.jd.lie.mine.presenter.LoginPresenter;
import com.jd.lie.mine.ui.activity.FeedbackActivity;
import com.jd.lie.mine.ui.activity.FunctionActivity;
import com.jd.lie.mine.ui.activity.SetActivity;
import com.jd.lie.mine.ui.activity.UserInforActivity;
import com.jd.utils.ToastUtils;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;


import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * @author ：王文彬 on 2020/5/31 12：30
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.img_head)
    CircleImageView img_head;
    @BindView(R.id.tv_name)
    TextView tv_name;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        super.initView();
        Glide.with(getActivity()).load("http://storage.jd.com/liedetector-photo-public/20200609100413982195.png").into(img_head);
        tv_name.setText("123456");
    }


    @OnClick(R.id.rr_fw)
    void toFuwu() {
        ToastUtils.show("联系客服");
    }

    @OnClick(R.id.tv_set)
    void toSet() {
        startActivity(new Intent(getActivity(), SetActivity.class));
    }

    @OnClick(R.id.tv_gn)
    void toFunction() {
        startActivity(new Intent(getActivity(), FunctionActivity.class));
    }

    @OnClick(R.id.tv_fk)
    void toFeedBack() {
        startActivity(new Intent(getActivity(), FeedbackActivity.class));
    }

    @OnClick(R.id.tv_name)
    void toUserInfor() {
        startActivity(new Intent(getActivity(), UserInforActivity.class));
    }


}
