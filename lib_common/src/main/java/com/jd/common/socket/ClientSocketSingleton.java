package com.jd.common.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;


/**
 * 服务端的socket
 */
public class ClientSocketSingleton extends Socket {


  private static final String host = "192.168.43.110";
  private static final int port = 12345;
  private static BufferedReader br = null;
  private static PrintWriter pw = null;
  /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
  private static ClientSocketSingleton socket = null;

        /*private ClientSocketSingleton(String host, int port) throws UnknownHostException, IOException {
            super(host, port);
        }*/

  private ClientSocketSingleton() throws UnknownHostException, IOException {
    super();
  }

  public static ClientSocketSingleton getsocket(String host, int port) throws IOException {

    if (socket == null) {
      synchronized (ClientSocketSingleton.class) {
        if (socket == null) {
          ///socket= new ClientSocketSingleton(host,port);
          socket = new ClientSocketSingleton();
          SocketAddress socAddress = new InetSocketAddress(host, port);
          socket.connect(socAddress, 5000);  //超时时间

        }
      }

    }
    return socket;
  }


  public static BufferedReader getbr() throws IOException {
    if (br == null) {
      br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
    }
    return br;
  }

  public static PrintWriter getpw() throws IOException {
    if (pw == null) {
      pw = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true
      );
    }
    return pw;
  }


}
