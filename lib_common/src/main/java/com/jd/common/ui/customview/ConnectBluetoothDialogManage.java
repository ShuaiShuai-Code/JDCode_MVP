package com.jd.common.ui.customview;

import android.content.Context;

/**
 * @author ：王文彬 on 2020/3/10 16：17
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class ConnectBluetoothDialogManage {


  private ConnectBluetoothDialog mProgressDialog;

  public void showDialog(Context context, String address) {
    if (mProgressDialog == null) {
      mProgressDialog = new ConnectBluetoothDialog(context, false, true);
      mProgressDialog.setIpAddress(address);
    }
    if (!mProgressDialog.isShowing()) {
      mProgressDialog.show();
    }

    if (mProgressDialog != null && mProgressDialog.isShowing()) {
      mProgressDialog.setOnDialogListener(this::dismissDialog);
    }
  }

  public void dismissDialog() {
    if (mProgressDialog != null && mProgressDialog.isShowing()) {
      mProgressDialog.dismiss();
      mProgressDialog.cancel();
      mProgressDialog = null;
    }
  }
}
