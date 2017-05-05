package com.panda.zoo.netty.server.base;

/**
 * Created by huixiangdou on 2017/5/2.
 */
public interface Server {
    /**
     * 服务启动
     */
    void start();

    /**
     * 服务关闭
     */
    void close();
}
