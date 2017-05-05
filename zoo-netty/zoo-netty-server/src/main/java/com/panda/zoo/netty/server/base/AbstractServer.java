package com.panda.zoo.netty.server.base;

import com.google.common.collect.Iterables;
import com.panda.zoo.netty.common.constants.ZooNettyConstants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by huixiangdou on 2017/5/2.
 */
public abstract class AbstractServer implements Server {
    private static final Logger logger = LoggerFactory.getLogger(AbstractServer.class);
    private int serverPort = ZooNettyConstants.DEFAULT_PORT; // 默认端口
    private String serverName = "HttpServer";
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private ServerBootstrap serverBootstrap;
    private List<ChannelHandler> channelHandlers;

    public AbstractServer(int serverPort, String serverName) {
        this.serverName = serverName;
        this.serverPort = serverPort;

        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup);
    }

    protected abstract void init(ServerBootstrap serverBootstrap);

    @Override
    public void start() {
        init(serverBootstrap);
        ChannelFuture channelFuture;
        try {
            logger.info("server start --> name:{},port:{}", serverName, serverPort);
            channelFuture = serverBootstrap.bind(serverPort).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("[" + serverName + "] server start error", e);
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    @Override
    public void close() {
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public List<ChannelHandler> getChannelHandlers() {
        return channelHandlers;
    }

    public void setChannelHandlers(List<ChannelHandler> channelHandlers) {
        this.channelHandlers = channelHandlers;
    }
}
