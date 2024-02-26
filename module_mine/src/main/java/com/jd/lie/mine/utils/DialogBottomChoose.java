package com.jd.lie.mine.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.jd.lie.mine.R;


public class DialogBottomChoose extends Dialog implements View.OnClickListener {
    private Button btn_feed1, btn_feed2, btn_feed3, btn_feed4, btn_feed5, btn_cancel;

    private Window window;

    private Activity context;
    private MyOnClickListenter onClickListenter;


    public DialogBottomChoose(Activity context, MyOnClickListenter onClickListenter) {
        super(context);
        setContentView(R.layout.dialog_bottom_choose);
        this.context = context;
        this.onClickListenter = onClickListenter;
        window = getWindow();//得到对话框的窗口．
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.gravity = Gravity.BOTTOM;//设置显示在中间
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(wl);
        findViewById(R.id.btn_feed1).setOnClickListener(this);
        findViewById(R.id.btn_feed2).setOnClickListener(this);
        findViewById(R.id.btn_feed3).setOnClickListener(this);
        findViewById(R.id.btn_feed4).setOnClickListener(this);
        findViewById(R.id.btn_feed5).setOnClickListener(this);
        btn_cancel=findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                dismiss();
            }
        });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_feed1:
            case R.id.btn_feed2:
            case R.id.btn_feed3:
            case R.id.btn_feed4:
            case R.id.btn_feed5:
                TextView textView = findViewById(view.getId());
                onClickListenter.setString(textView.getText().toString());
                dismiss();
                break;
        }
    }
}
