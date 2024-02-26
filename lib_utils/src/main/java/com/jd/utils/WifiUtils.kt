package com.jd.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresPermission
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException

/**
 * @author ：王文彬 on 2020/5/6 17：08
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
class WifiUtils {
  
  
  companion object {
    
    /**
     * 网络是否可用
     */
    @JvmStatic
    @Suppress("DEPRECATION")
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    fun isNetWorkAvailable(context: Context): Boolean {
      var result = false
      val connectivityManager =
          context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager?.run {
          getNetworkCapabilities(activeNetwork)?.run {
            result = when {
              hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
              hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
              hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
              else -> false
            }
          }
        }
      } else {
        connectivityManager?.run {
          activeNetworkInfo?.run {
            if (type == ConnectivityManager.TYPE_WIFI) {
              result = true
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
              result = true
            }
          }
        }
      }
      return result
    }
    
    /**
     * 是否连接的是WiFi
     */
    @JvmStatic
    @Suppress("DEPRECATION")
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    fun isConnectWifi(context: Context): Boolean {
      var result = false
      val connectivityManager =
          context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager?.run {
          getNetworkCapabilities(activeNetwork)?.run {
            result = when {
              hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
              else -> false
            }
          }
        }
      } else {
        connectivityManager?.run {
          activeNetworkInfo?.run {
            if (type == ConnectivityManager.TYPE_WIFI) {
              result = true
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
              result = false
            }
          }
        }
      }
      return result
    }
    
    /**
     * WiFi是否可用
     */
    @JvmStatic
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    fun isWifiAvailable(context: Context): Boolean {
      val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
      return when (wifiManager.wifiState) {
        WifiManager.WIFI_STATE_ENABLED -> true
        else -> false
      }
    }
    
    /**
     * WiFi是否可用
     */
    @JvmStatic
    fun isWifiEnabled(context: Context): Boolean = (context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager).isWifiEnabled
    
    /**
     * 获取 WifiManager
     */
    @JvmStatic
    fun getWifiManager(context: Context): WifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    
    /**
     * 获取 wifiState
     */
    @JvmStatic
    fun wifiState(context: Context): Int = (context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager).wifiState
    
    /**
     * 打开WiFi设置界面
     */
    @JvmStatic
    fun openWifiSetting(activity: Activity) {
      activity.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
    }
    
    /**
     * 获取IP地址
     */
    @JvmStatic
    @SuppressWarnings("ALL")
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getIpAddress(context: Context): String? {
      
      val connectivityManager =
          context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager?.run {
          getNetworkCapabilities(activeNetwork)?.run {
            when {
              hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                val wifiManager =
                    context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val wifiInfo = wifiManager.connectionInfo
                return intIP2StringIP(wifiInfo.ipAddress)
              }
              hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                try {
                  val enumeration = NetworkInterface.getNetworkInterfaces()
                  while (enumeration.hasMoreElements()) {
                    val networkInterface = enumeration.nextElement()
                    val addresses = networkInterface.inetAddresses
                    while (addresses.hasMoreElements()) {
                      val inetAddress = addresses.nextElement()
                      if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                        return inetAddress.getHostAddress()
                      }
                    }
                  }
                } catch (e: SocketException) {
                  e.printStackTrace()
                  return null
                }
              }
              else -> null
            }
          }
        }
      } else {
        val info =
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        if (info != null && info.isConnected) {
          if (info.type == ConnectivityManager.TYPE_MOBILE) {
            try {
              val en = NetworkInterface.getNetworkInterfaces()
              while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr = intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                  val inetAddress = enumIpAddr.nextElement()
                  if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                    return inetAddress.getHostAddress()
                  }
                }
              }
            } catch (e: SocketException) {
              e.printStackTrace()
              return null
            }
          } else if (info.type == ConnectivityManager.TYPE_WIFI) {
            val wifiManager =
                context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val wifiInfo = wifiManager.connectionInfo
            return intIP2StringIP(wifiInfo.ipAddress)
          }
        }
      }
      return null
    }
    
    /**
     * 获取SSD地址
     */
    @JvmStatic
    @SuppressWarnings("ALL")
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getSSID(context: Context): String? {
      
      val connectivityManager =
          context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager?.run {
          getNetworkCapabilities(activeNetwork)?.run {
            when {
              hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                val wifiManager =
                    context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val wifiInfo = wifiManager.connectionInfo
                return wifiInfo.ssid
              }
              else -> ""
            }
          }
        }
      } else {
        val info =
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        if (info != null && info.isConnected) {
          if (info.type == ConnectivityManager.TYPE_WIFI) {
            val wifiManager =
                context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val wifiInfo = wifiManager.connectionInfo
            return wifiInfo.ssid
          } else {
            return ""
          }
        }
      }
      return ""
    }
    
    /**
     * int ip 地址转 String
     */
    @JvmStatic
    fun intIP2StringIP(ip: Int): String? {
      return (ip and 0xFF).toString() + "." +
          (ip shr 8 and 0xFF) + "." +
          (ip shr 16 and 0xFF) + "." +
          (ip shr 24 and 0xFF)
    }
  }
}