package com.jd.common.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jd.common.R;
import com.jd.utils.StringUtils;

import androidx.core.content.ContextCompat;

/**
 * @author ：王文彬 on 2020-02-05 14：47
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class NameInputView extends RelativeLayout {


  private EditText mInputName;

  public NameInputView(Context context) {
    super(context);
  }

  public NameInputView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initView(context, attrs);
  }

  public NameInputView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  private void initView(Context context, AttributeSet attrs) {

    View view = View.inflate(context, R.layout.item_textview_widget, null);
    TextView mName = view.findViewById(R.id.tv_name_location);
    mInputName = view.findViewById(R.id.et_input_name);
    ImageView mIvDelete = view.findViewById(R.id.iv_delete);

    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NameInputView);
    settingName(mName, typedArray, context);
    settingRightImage(mIvDelete, typedArray);
    settingInputName(mInputName, mIvDelete);
    typedArray.recycle();
    addView(view);
  }

  private void settingRightImage(ImageView mIvDelete, TypedArray typedArray) {
    int rightImgResId = typedArray.getResourceId(R.styleable.NameInputView_rightImage, R.mipmap.icon_click);
    int rightImageMargin = (int) typedArray.getDimension(R.styleable.NameInputView_rightImageMargin, 0);
    int rightImageVisible = typedArray.getInteger(R.styleable.NameInputView_rightImageVisible, View.GONE);
    LayoutParams params = new LayoutParams(mIvDelete.getLayoutParams());
    params.setMargins(0, 0, rightImageMargin, 0);
    params.addRule(RelativeLayout.ALIGN_PARENT_END);
    params.addRule(RelativeLayout.CENTER_VERTICAL);
    mIvDelete.setLayoutParams(params);
    mIvDelete.setVisibility(rightImageVisible);
    mIvDelete.setBackgroundResource(rightImgResId);
    mIvDelete.setPadding(0, 0, rightImageMargin, 0);
  }

  private void settingName(TextView mName, TypedArray typedArray, Context context) {
    String mLeftText = typedArray.getString(R.styleable.NameInputView_leftText);
    int leftTextColor = typedArray.getColor(R.styleable.NameInputView_leftTextColor,
        ContextCompat.getColor(context, R.color.black));
    float leftTextSize = typedArray.getDimension(R.styleable.NameInputView_leftTextSize, 0);
    int leftTextMargin = (int) typedArray.getDimension(R.styleable.NameInputView_leftTextMargin, 0);
    mName.setText(mLeftText);
    mName.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
    mName.setTextColor(leftTextColor);
    mName.setPadding(leftTextMargin, 0, 0, 0);
  }

  private void settingInputName(EditText mInputName, ImageView mIvDelete) {

    mInputName.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(10)});
    mInputName.setSelection(mInputName.getText().length());

    mInputName.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(StringUtils.getEditTextString(mInputName))) {
          mIvDelete.setVisibility(android.view.View.VISIBLE);
        } else {
          mIvDelete.setVisibility(android.view.View.GONE);
        }
      }
    });

    mIvDelete.setOnClickListener(v -> mInputName.setText(""));
  }

  private InputFilter filter = (source, start, end, dest, dstart, dend) -> {
    for (int i = start; i < end; i++) {
      if (!isChinese(source.charAt(i))) {
        return "";
      }
    }
    return null;
  };

  private boolean isChinese(char c) {
    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
    return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
        || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
        || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
        || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
        || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
        || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
  }


  public EditText getInputView() {
    return mInputName;
  }

  public String getInputViewTxt() {
    if (!TextUtils.isEmpty(StringUtils.getEditTextString(mInputName))) {
      return StringUtils.getEditTextString(mInputName);
    }
    return "";
  }

}
