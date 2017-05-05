package com.panda.zoo.netty.server.container;

import com.panda.zoo.netty.server.base.AbstractServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Created by huixiangdou on 2017/5/2.
 * 默认的http服务
 */
public class DefaultHttpServer extends AbstractServer {
    private ChannelHandler channelHandler;

    public DefaultHttpServer(int serverPort, String serverName, ChannelHandler channelHandler) {
        super(serverPort, serverName);
        this.channelHandler = channelHandler;
    }

    @Override
    protected void init(ServerBootstrap serverBootstrap) {
        serverBootstrap.channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new HttpServerCodec());
                ch.pipeline().addLast("handler", channelHandler);
            }
        });
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
        serverBootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
        serverBootstrap.option(ChannelOption.SO_TIMEOUT, 3000);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
    }
}
