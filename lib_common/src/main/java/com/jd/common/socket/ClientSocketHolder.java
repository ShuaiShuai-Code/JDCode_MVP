package com.jd.common.socket;

import java.net.Socket;


/**
 * 单例模式  保存初始化时建立得socket连接
 *
 * @author haoshuo6
 */

public class ClientSocketHolder {


  private Socket socket;  //蓝牙连接

  private volatile static ClientSocketHolder singleton;

  public static ClientSocketHolder getSingleton() {

    if (singleton == null) {
      synchronized (ClientSocketHolder.class) {
        if (singleton == null) {
          singleton = new ClientSocketHolder();

        }
      }

    }
    return singleton;
  }


  public Socket getSocket() {
    return socket;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }

}
