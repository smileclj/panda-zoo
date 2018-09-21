package com.panda.zoo.common.tcp.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author huixiangdou
 * @date 2018/8/16
 */
public class SocketServer {
    private final int PORT = 6667;
    private final int BACKLOG = 1;

    private void start() {
        try {
            ServerSocket server = new ServerSocket(PORT, BACKLOG);
            System.out.println("server start");
            Socket socket;
            while (true) {
                socket = server.accept();
                new Thread(new Handler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Handler implements Runnable {
        private Socket socket;
        private final Charset CHARSET = Charset.forName("utf-8");

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, CHARSET);
                BufferedReader br = new BufferedReader(isr);

                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET);
                PrintWriter pw = new PrintWriter(osw);

                String msg;
                while ((msg = br.readLine()) != null) {
                    System.out.println("收到信号：" + msg);
                    pw.println("黄河黄河");
                    pw.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new SocketServer().start();
    }
}
