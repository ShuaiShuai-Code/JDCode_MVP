package com.jd.common.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jd.common.R;
import com.jd.common.constant.Constant;
import com.jd.utils.AppManagerUtils;
import com.jd.utils.LogUtils;
import com.jd.utils.SpUtils;
import com.jd.utils.StatusBarUtils;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import org.simple.eventbus.EventBus;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import butterknife.ButterKnife;

/**
 * @author ：王文彬 on 2020-02-05 11：37
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    private BaseActivity mThisActivity = null;
    private TextView tvMainMiddleTitle;
    private ImageView ivMainLeftBtn;
    private ImageView ivMainRightImgBtn;
    private TextView tvMainRightTxtBtn;
    private TextView tvSubLeftTitle;
    private ImageView ivSubBack;
    protected float textSizeMagnification=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, initLayout(), null);
        setContentView(needHeader() ? getMergerView(view, chooseHeader()) : view);
        init();
        initParams();
        initView();
        initAdapter();
        initNet();
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /**
     * @return 初始化布局
     */
    protected abstract @LayoutRes
    int initLayout();

    /**
     * 是否需要封装的标题
     *
     * @return 默认不需要
     */
    protected boolean needHeader() {
        return false;
    }

    /**
     * 选择需要使用哪种标题
     *
     * @return 默认为主标题
     */
    protected Header chooseHeader() {
        return Header.MAIN;
    }

    private void init() {
        ButterKnife.bind(this);
        AppManagerUtils.getInstance().addActivity(this);
        StatusBarUtils.translucent(this);
        mThisActivity = this;
    }

    /**
     * 初始化参数的方法，例如bundle等
     */
    protected void initParams() {
    }

    /**
     * 初始化布局的方法，例如findviewbyid等
     */
    protected void initView() {
    }

    /**
     * 初始网络的方法
     */
    protected void initNet() {
    }

    /**
     * 初始Adapter的方法
     */
    protected void initAdapter() {
    }

    /**
     * 初始点击事件的方法
     */
    protected void initListener() {
    }

    @Override
    public Resources getResources() {
        float scale =
                (float) SpUtils.get(this, Constant.SP_FontScale, 1f);
        Resources res =super.getResources();
        Configuration config = res.getConfiguration();
        config.fontScale= scale;//1 设置正常字体大小的倍数
        res.updateConfiguration(config,res.getDisplayMetrics());
        LogUtils.w("fontSizeScale" + scale);
        return res;


    }

    private View getMergerView(@NonNull View v, Header header) {
        View rootView = View.inflate(this, R.layout.view_base_activity, null);
        if (header.equals(Header.MAIN)) {
            rootView.findViewById(R.id.rl_main_content).setVisibility(View.VISIBLE);
            tvMainMiddleTitle = rootView.findViewById(R.id.tv_middle_title);
            ivMainLeftBtn = rootView.findViewById(R.id.iv_left_img);
            ivMainRightImgBtn = rootView.findViewById(R.id.iv_right_img);
            tvMainRightTxtBtn = rootView.findViewById(R.id.tv_right_txt);
            LinearLayout llContainer = rootView.findViewById(R.id.ll_act_container);
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
            ivSubBack = rootView.findViewById(R.id.iv_sub_back);
            ivSubBackBtnClickListener();
            llSubContainer.addView(v);
        }
        return rootView;
    }

    protected void ivSubBackBtnClickListener() {
        if (ivSubBack != null) {
            ivSubBack.setOnClickListener(v -> mThisActivity.finish());
        }
    }


    private void leftBtnClickListener() {
        if (ivMainLeftBtn != null) {
            ivMainLeftBtn.setOnClickListener(v -> mThisActivity.finish());
        }
    }

    private void rightImgBtnClickListener() {
        if (ivMainRightImgBtn != null) {
            ivMainRightImgBtn.setOnClickListener(v -> setOnMainRightImgBtnClickListener());
        }
    }

    /**
     * 主标题左侧TextView点击事件
     */
    private void rightTxtBtnClickListener() {
        if (tvMainRightTxtBtn != null) {
            tvMainRightTxtBtn.setOnClickListener(v -> setOnMainRightTxtBtnClickListener());
        }
    }

    /**
     * 主标题右侧ImageView点击事件
     */
    protected void setOnMainRightImgBtnClickListener() {

    }

    /**
     * 主标题右侧TextView点击事件
     */
    protected void setOnMainRightTxtBtnClickListener() {

    }

    /**
     * 设置主标题TextView内容
     */
    @Override
    public void setTitle(CharSequence title) {
        if (tvMainMiddleTitle != null) {
            tvMainMiddleTitle.setText(title);
        }
    }

    /**
     * 获取主标题左侧侧TextView
     */
    protected ImageView getMainLeftBtnView() {
        if (null != ivMainLeftBtn) {
            return ivMainLeftBtn;
        }
        return null;
    }

    /**
     * 获取主标题右侧ImageView
     */
    protected ImageView getMainRightImgBtnView() {
        if (null != ivMainRightImgBtn) {
            return ivMainRightImgBtn;
        }
        return null;
    }

    /**
     * 获取主标题右侧TextView
     */
    protected TextView getMainRightTxtBtnView() {
        if (null != tvMainRightTxtBtn) {
            return tvMainRightTxtBtn;
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
     * 获取次级标题View
     */
    protected ImageView getIvSubBackBtnView() {
        if (null != ivSubBack) {
            return ivSubBack;
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
            Log.d("TAG", "setTvSubLeftTitle: " + title);
            tvSubLeftTitle.setText(title);
        }
    }


    /**
     * 获取当前页面的Activity
     *
     * @return Activity
     */
    public BaseActivity getThisActivity() {
        return mThisActivity;
    }

    /**
     * 获取当前页面的Activity的名称
     *
     * @return Activity的名称
     */
    public String getThisActivityName() {
        return mThisActivity.getClass().getName();
    }

    /**
     * 跳转到指定的页面，默认会关闭当前页面，会从Activity栈中移除该Activity
     *
     * @param clazz Class
     */
    public void gotoActivity(Class<?> clazz) {
        gotoActivity(clazz, false);
    }

    /**
     * 跳转到指定的页面
     *
     * @param clazz Class
     */
    public void gotoActivity(Class<?> clazz, boolean isFinish) {
        mThisActivity.startActivity(new Intent(mThisActivity, clazz));
        if (isFinish) {
            mThisActivity.finish();
        }
    }

    /**
     * 携带bundle跳转到指定的页面
     *
     * @param clazz Class
     */
    public void gotoActivity(@NonNull Class<?> clazz, @NonNull Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(mThisActivity, clazz);
        intent.putExtras(bundle);
        mThisActivity.startActivity(intent);
        if (isFinish) {
            mThisActivity.finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mThisActivity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().getStickyEvents().clear();
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

