package com.jd.utils

import android.bluetooth.BluetoothSocket
import java.net.Socket

/**
 * @describe ：
 * @author ：王文彬 on 2020/5/8 11：06
 * @email ：wangwenbin29@jd.com
 */
object SaveSocketUtil {
  
  
  private val mSocketList: MutableList<Socket> by lazy {
    mutableListOf<Socket>()
  }
  
  private val mBluetoothSocketList: MutableList<BluetoothSocket> by lazy {
    mutableListOf<BluetoothSocket>()
  }
  
  
  fun setSocket(socket: Socket) {
    if (socketListNotEmpty()) mSocketList.clear()
    mSocketList.add(socket)
  }
  
  fun setBluetoothSocket(bluetoothSocket: BluetoothSocket) {
    if (bluetoothSocketListNotEmpty()) mBluetoothSocketList.clear()
    mBluetoothSocketList.add(bluetoothSocket)
  }
  
  fun getSocketList() = mSocketList
  
  fun getBluetoothSocketList() = mBluetoothSocketList
  
  fun socketListNotEmpty(): Boolean = mSocketList.size > 0
  
  fun socketIsConnect():Boolean = mSocketList.size > 0&& mSocketList[0].isConnected
  
  fun bluetoothSocketIsConnect():Boolean = mBluetoothSocketList.size > 0&& mBluetoothSocketList[0].isConnected
  
  fun bluetoothSocketListNotEmpty(): Boolean = mBluetoothSocketList.size > 0
  
}