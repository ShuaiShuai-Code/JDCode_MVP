package com.jd.common.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.jd.common.constant.Constant;

import androidx.annotation.RequiresPermission;


/**
 * @author hao
 * 注意有些设备再搜索的过程中，是没有Name这个属性的注意判定空值
 */


public class BluetoothReceiver extends BroadcastReceiver {
  private static final String TAG = BluetoothReceiver.class.getSimpleName();

  public static final String pin = "0438";  //此处为你要连接的蓝牙设备的初始密钥，一般为1234或0000

  public BluetoothReceiver() {

  }

  //广播接收器，当远程蓝牙设备被发现时，回调函数onReceiver()会被执行

  @Override
  @RequiresPermission(Manifest.permission.BLUETOOTH)
  public void onReceive(Context context, Intent intent) {

    String action = intent.getAction(); //得到action
    Log.d(TAG, action);
    BluetoothDevice btDevice = null;  //创建一个蓝牙device对象
    // 从Intent中获取设备对象
    /**
     * 对于每被发现的一个设备，系统将会广播ACTION_FOUND
     * 该Intent包含两个额外域，EXTRA_DEVICE 和 EXTRA_CLASS。分别包含一个BluetoothDevice类对象和BluetoothClass类对象。
     */
    btDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

    if (BluetoothDevice.ACTION_FOUND.equals(action)) {  //发现设备

      String temp = btDevice.getName(); //temp的值可能为null

      if (temp != null && temp.contains(Constant.DEVICE_NAME))//HC-05设备如果有多个，第一个搜到的那个会被尝试。
      {
        if (btDevice.getBondState() == BluetoothDevice.BOND_NONE) {

          Log.e(TAG, "attemp to bond:" + "[" + btDevice.getName() + "]");
          try {
            //通过工具类ClsUtils,调用createBond方法
            boolean bondResult = ClsUtils.createBond(btDevice.getClass(), btDevice);
            Log.d(TAG, "onReceive: 配对结果是-->" + bondResult);
            if (bondResult) {
              Toast.makeText(context, "配对成功", Toast.LENGTH_SHORT).show();
            } else {
              Toast.makeText(context, "配对失败", Toast.LENGTH_SHORT).show();

            }
          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      } else {
        Log.e(TAG, "不是目标设备");
      }

    } else if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) //再次得到的action，会等于PAIRING_REQUEST
    {
      Log.e(TAG, "action2=" + action);
      String deviceName = btDevice.getName();
      if (deviceName != null && deviceName.contains(Constant.DEVICE_NAME)) {
        Log.e(TAG, "开始配对");

        try {

          //1.确认配对
          ClsUtils.setPairingConfirmation(btDevice.getClass(), btDevice, true);
          //2.终止有序广播
          Log.i(TAG,
              "isOrderedBroadcast:" + isOrderedBroadcast() + ",isInitialStickyBroadcast:" + isInitialStickyBroadcast());
          abortBroadcast();//如果没有将广播终止，则会出现一个一闪而过的配对框。
          //3.调用setPin方法进行配对...
          boolean ret = ClsUtils.setPin(btDevice.getClass(), btDevice, pin);

        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      } else {
        Log.e(TAG, "这个设备不是目标蓝牙设备");
      }

    }
  }


}
