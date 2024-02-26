package com.jd.common.ui.customview;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.jd.common.R;


/**
 * @author ：王文彬 on 2020/3/10 14：55
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class TrialCitEditMainProblemDialog extends BaseDialog {

    private Button btnCancel;
    private Button btnSure;

    public TrialCitEditMainProblemDialog(@NonNull Context context) {
        super(context);
    }

    public TrialCitEditMainProblemDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    public TrialCitEditMainProblemDialog(@NonNull Context context, int theme, Boolean isBackCancelable) {
        super(context, theme, isBackCancelable);
    }

    public TrialCitEditMainProblemDialog(@NonNull Context context, int theme, Boolean isBackCancelable,
                                         float widthOffset) {
        super(context, theme, isBackCancelable, widthOffset);
    }

    public TrialCitEditMainProblemDialog(Context context, boolean isCancelable, boolean isBackCancelable) {
        super(context, isCancelable, isBackCancelable);
    }

    @Override
    protected View setDialogLayout() {
        View view = View.inflate(getContext(), R.layout.dialog_edit_trial_cit_main_problem, null);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnSure = view.findViewById(R.id.btn_sure);
        initListener();
        return view;
    }

    private void initListener() {
        btnCancel.setOnClickListener(v -> {
            if (mOnDialogListener != null) {
                mOnDialogListener.onCancel();
            }
        });
        btnSure.setOnClickListener(v -> {
            if (mOnDialogListener != null) {
                mOnDialogListener.onSure();
            }
        });
    }


    private OnDialogListener mOnDialogListener = null;

    public void setOnDialogListener(OnDialogListener onDialogListener) {
        this.mOnDialogListener = onDialogListener;
    }

    public interface OnDialogListener {
        void onCancel();

        void onSure();
    }

}
