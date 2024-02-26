package com.jd.common.ui.customview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jd.common.R;

import androidx.annotation.NonNull;


/**
 * @author ：王文彬 on 2020/3/10 14：55
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class ConnectBluetoothDialog extends BaseDialog {

  private TextView tvIpAddress;
  private ImageView ivCloseDialog;

  public ConnectBluetoothDialog(@NonNull Context context) {
    super(context);
  }

  public ConnectBluetoothDialog(@NonNull Context context, int theme) {
    super(context, theme);
  }

  public ConnectBluetoothDialog(@NonNull Context context, int theme, Boolean isBackCancelable) {
    super(context, theme, isBackCancelable);
  }

  public ConnectBluetoothDialog(@NonNull Context context, int theme, Boolean isBackCancelable, float widthOffset) {
    super(context, theme, isBackCancelable, widthOffset);
  }

  public ConnectBluetoothDialog(Context context, boolean isCancelable, boolean isBackCancelable) {
    super(context, isCancelable, isBackCancelable);
  }

  @Override
  protected View setDialogLayout() {
    View view = View.inflate(getContext(), R.layout.dialog_connect_bluetooth, null);
    tvIpAddress = view.findViewById(R.id.tv_ip_address);
    ivCloseDialog = view.findViewById(R.id.iv_close_dialog);
    ivCloseDialog.setOnClickListener(v -> {
      if (mOnDialogListener != null) {
        mOnDialogListener.onClose();
      }
    });
    return view;
  }

  public void setIpAddress(String str) {
    if (tvIpAddress != null) {
      tvIpAddress.setText(str);
    }
  }

  public TextView getIpAddressView() {
    if (tvIpAddress != null) {
      return tvIpAddress;
    }
    return null;
  }

  public ImageView getCloseDialogView() {
    if (ivCloseDialog != null) {
      return ivCloseDialog;
    }
    return null;
  }

  private OnDialogListener mOnDialogListener = null;

  public void setOnDialogListener(OnDialogListener onDialogListener) {
    this.mOnDialogListener = onDialogListener;
  }

  interface OnDialogListener {
    void onClose();
  }

}
