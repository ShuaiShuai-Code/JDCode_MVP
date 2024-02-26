package com.jd.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.*
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import java.io.IOException
import java.util.*


/**
 * @describe ：
 * @author ：王文彬 on 2020/5/6 15：29
 * @email ：wangwenbin29@jd.com
 */
const val OPEN_BLUETOOTH_REQUEST_CODE = 0

class BluetoothUtil {
  
  
  companion object {
    
    private val mBluetoothAdapter: BluetoothAdapter by lazy {
      BluetoothAdapter.getDefaultAdapter()
    }
    
    /**
     * 判断设备是否支持蓝牙
     */
    @JvmStatic
    fun isSupportBluetooth(context: Context): Boolean {
      val bluetoothManger = context.applicationContext.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
      val bluetoothAdapter = bluetoothManger.adapter
      return bluetoothAdapter != null
    }
    
    /**
     * 判断蓝牙是否开启
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.BLUETOOTH)
    fun isBluetoothOn(): Boolean = mBluetoothAdapter.isEnabled
    
    
    /**
     * 打开蓝牙
     */
    @JvmStatic
    @SuppressLint("MissingPermission")
    @RequiresPermission(Manifest.permission.BLUETOOTH_ADMIN)
    fun openBluetooth(): Boolean {
      return if (!isBluetoothOn()) {
        mBluetoothAdapter.enable()
      } else {
        true
      }
    }
    
    /**
     * 打开蓝牙
     */
    @JvmStatic
    fun openBluetoothForPrivate(activity: Activity) {
      val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
      activity.startActivityForResult(intent, OPEN_BLUETOOTH_REQUEST_CODE)
    }
    
    /**
     * 获取BluetoothAdapter
     */
    @JvmStatic
    fun getBluetoothAdapter() = mBluetoothAdapter
    
    
    /**
     * 获取本地蓝牙名称
     */
    @JvmStatic
    fun getBluetoothName() = mBluetoothAdapter.name
    
    /**
     * 获取本地蓝牙地址
     */
    @SuppressLint("HardwareIds")
    @JvmStatic
    fun getBluetoothAddress() = mBluetoothAdapter.address
    
    /**
     * 获取所有已配对的设备
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.BLUETOOTH)
    fun getPairedDevices(): MutableList<BluetoothDevice> {
      val deviceList = mutableListOf<BluetoothDevice>()
      val bondedDevices = mBluetoothAdapter.bondedDevices
      if (bondedDevices.size > 0) {
        bondedDevices.forEach {
          deviceList.add(it)
        }
      }
      /*  bondedDevices.takeIf {
            it.size > 0
        }.let {
            it?.forEach {
                deviceList.add(it)
            }
        }*/
      return deviceList
    }
    
    /**
     * 获取所有已配对的打印类设备
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.BLUETOOTH)
    fun getPairedPrinterDevices(): MutableList<BluetoothDevice> =
        getSpecificDevice(BluetoothClass.Device.Major.IMAGING)
    
    
    /**
     * 从已配对设配中，删选出某一特定类型的设备展示
     * @param deviceClass
     * @return
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.BLUETOOTH)
    fun getSpecificDevice(deviceClass: Int): MutableList<BluetoothDevice> {
      val pairedDevices = getPairedDevices()
      val printerDevices = mutableListOf<BluetoothDevice>()
      pairedDevices.forEach {
        val klass: BluetoothClass = it.getBluetoothClass()
        if (klass.majorDeviceClass == deviceClass) {
          printerDevices.add(it)
        }
      }
      return printerDevices
    }
    
    @JvmStatic
    @RequiresPermission(Manifest.permission.BLUETOOTH)
    fun connectDevice(device: BluetoothDevice, uuid: UUID): BluetoothSocket? {
      var socket: BluetoothSocket? = null
      try {
        socket = device.createInsecureRfcommSocketToServiceRecord(uuid)
        socket.connect()
      } catch (e: IOException) {
        e.printStackTrace()
        try {
          socket?.close()
        } catch (closeException: IOException) {
          e.printStackTrace()
          return socket
        }
        return socket
      }
      return socket
    }
    
  }
}