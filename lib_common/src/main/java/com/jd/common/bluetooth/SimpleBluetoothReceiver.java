package com.jd.common.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.jd.common.constant.Constant;

import androidx.annotation.RequiresPermission;


/**
 * @author hao
 * 注意有些设备再搜索的过程中，是没有Name这个属性的注意判定空值
 */


public class SimpleBluetoothReceiver extends BroadcastReceiver {

  //此处为你要连接的蓝牙设备的初始密钥，一般为1234或0000
  private static final String PIN = "0438";


  @Override
  @RequiresPermission(Manifest.permission.BLUETOOTH)
  public void onReceive(Context context, Intent intent) {
    String action = intent.getAction();
    //创建一个蓝牙device对象
    BluetoothDevice btDevice = null;
    // 从Intent中获取设备对象 对于每被发现的一个设备，系统将会广播ACTION_FOUND 该Intent包含两个额外域，EXTRA_DEVICE 和
    // EXTRA_CLASS。分别包含一个BluetoothDevice类对象和BluetoothClass类对象。
    btDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
    String resultShow = "未发现匹配设备，请检查";
    if (BluetoothDevice.ACTION_FOUND.equals(action)) {
      String temp = btDevice.getName();
      //HC-05设备如果有多个，第一个搜到的那个会被尝试。
      if (temp != null && temp.contains(Constant.DEVICE_NAME)) {
        if (btDevice.getBondState() == BluetoothDevice.BOND_NONE) {
          try {
            boolean bondResult = ClsUtils.createBond(btDevice.getClass(), btDevice);
            if (bondResult) {
              Toast.makeText(context, "配对成功", Toast.LENGTH_SHORT).show();
              resultShow = "设备：" + "[" + btDevice.getName() + "]" + ":" + btDevice.getAddress() + "配对成功";
            } else {
              Toast.makeText(context, "配对失败", Toast.LENGTH_SHORT).show();
              resultShow = "设备：" + "[" + btDevice.getName() + "]" + ":" + btDevice.getAddress() + "配对失败";
            }
            listener.getMatchResult(resultShow);
          } catch (Exception e) {
            e.printStackTrace();
          }
        } else if (btDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
          resultShow = "设备：" + "[" + btDevice.getName() + "]" + ":" + btDevice.getAddress() + "已经绑定过";
          listener.getMatchResult(resultShow);
        }
      }
    } else if ("android.bluetooth.device.action.PAIRING_REQUEST".equals(action)) { //再次得到的action，会等于PAIRING_REQUEST
      String deviceName = btDevice.getName();
      if (deviceName != null && deviceName.contains(Constant.DEVICE_NAME)) {
        try {
          //1.确认配对
          ClsUtils.setPairingConfirmation(btDevice.getClass(), btDevice, true);
          //2.终止有序广播
          abortBroadcast();//如果没有将广播终止，则会出现一个一闪而过的配对框。
          //3.调用setPin方法进行配对...
          boolean ret = ClsUtils.setPin(btDevice.getClass(), btDevice, PIN);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
      listener.onDisFinish();

    } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
      listener.onStartDiscovery();
    }
  }

  private MatchResultListener listener;

  public interface MatchResultListener {

    void getMatchResult(String result);

    void onDisFinish();

    void onStartDiscovery();
  }

  public void setOnMatchResultListener(MatchResultListener listener) {
    this.listener = listener;
  }


}
