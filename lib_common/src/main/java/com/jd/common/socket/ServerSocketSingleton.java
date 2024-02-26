package com.jd.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/**
 * 双重锁的模式
 */


public class ServerSocketSingleton extends ServerSocket {

  private static final int port = 1989;
  private static BufferedReader br = null;
  private static PrintWriter pw = null;
  /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
  private volatile static ServerSocketSingleton socket = null;

  private ServerSocketSingleton(int port) throws UnknownHostException, IOException {
    super(port);
  }

  public static ServerSocketSingleton getSocket(int port) throws IOException {
    if (socket == null) {
      synchronized (ServerSocket.class) {
        if (socket == null) {
          socket = new ServerSocketSingleton(port);
        }
      }

    }
    return socket;
  }

    /*public static BufferedReader getbr() throws IOException {
        if(br==null){
            br=new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
        }
        return br;
    }

    public static PrintWriter getpw() throws IOException {
        if(pw==null){
            pw=new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true
            );
        }
        return pw;
    }*/


}
