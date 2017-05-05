package com.panda.zoo.netty.server;

import com.panda.zoo.netty.server.container.DefaultHttpServer;
import com.panda.zoo.netty.server.handler.DefaultHttpServerHandler;

/**
 * Created by huixiangdou on 2017/5/3.
 */
public class Start {
    public static void main(String[] args) {
        DefaultHttpServer server = new DefaultHttpServer(9999, "server", new DefaultHttpServerHandler());
        server.start();
    }
}
