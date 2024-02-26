package com.jd.utils

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresPermission
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException

/**
 * @describe ：网络请求工具类
 * @author ：王文彬 on 2020/4/14 17：27
 * @email ：wangwenbin29@jd.com
 */
class NetworkUtils {
  companion object {
    
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
    
    @SuppressWarnings("ALL")
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
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
    
    fun intIP2StringIP(ip: Int): String? {
      return (ip and 0xFF).toString() + "." +
          (ip shr 8 and 0xFF) + "." +
          (ip shr 16 and 0xFF) + "." +
          (ip shr 24 and 0xFF)
    }
    
    
  }
}