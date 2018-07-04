package com.panda.zoo.dubbo.provider.service.internal;

import java.util.concurrent.Future;

/**
 * @author huixiangdou
 * @date 2018/7/4
 */
public interface IAsyncTestService {
    Future<String> async1();

    Future<String> async2();

    void async3();

    Future<String> async4();
}
