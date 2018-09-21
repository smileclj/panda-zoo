package com.panda.zoo.common.tcp.socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author huixiangdou
 * @date 2018/8/17
 */
public class SocketClient {
    private final String HOST = "127.0.0.1";
    private final int PORT = 6667;
    private final Charset CHARSET = Charset.forName("utf-8");

    private void start() {
        try {
            Socket socket = new Socket(HOST, PORT);
            Scanner sc = new Scanner(System.in);
            System.out.println(socket.isConnected());

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET);
            PrintWriter pw = new PrintWriter(osw);

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, CHARSET);
            BufferedReader br = new BufferedReader(isr);

            while (true) {
                System.out.println("请说：");
                String s = sc.next();
                pw.println(s);
                pw.flush();
                String result = br.readLine();
                System.out.println("收到回答：" + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Runnable r = () -> new SocketClient().start();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
