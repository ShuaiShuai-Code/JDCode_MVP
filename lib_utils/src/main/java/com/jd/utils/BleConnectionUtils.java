package com.jd.utils;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;

/**
 * @author haoshuo6
 * @description 获取并保存Bluetooth的连接的socket
 */


public class BleConnectionUtils {


  BluetoothSocket bleSocket;  //蓝牙连接


  private volatile static BleConnectionUtils singleton;

  public static BleConnectionUtils getSingleton() {

    if (singleton == null) {
      synchronized (BleConnectionUtils.class) {
        if (singleton == null) {
          singleton = new BleConnectionUtils();

        }
      }

    }
    return singleton;
  }


  public BluetoothSocket getBleSocket() {
    return bleSocket;
  }

  public void setBleSocket(BluetoothSocket bleSocket) {
    this.bleSocket = bleSocket;
  }


  public void closeSocket() {
    if (null != bleSocket) {
      try {
        bleSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }

  }


}
