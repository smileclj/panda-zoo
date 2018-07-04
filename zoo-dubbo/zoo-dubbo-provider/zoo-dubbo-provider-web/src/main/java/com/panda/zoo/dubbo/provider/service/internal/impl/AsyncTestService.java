package com.panda.zoo.dubbo.provider.service.internal.impl;

import com.panda.zoo.dubbo.provider.service.internal.IAsyncTestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author huixiangdou
 * @date 2018/7/4
 */
@Service
public class AsyncTestService implements IAsyncTestService {
    @Override
    @Async("pool")
    public Future<String> async1() {
        System.out.println(1 / 0);
        return new AsyncResult<>("1");
    }

    @Override
    @Async("pool")
    public Future<String> async2() {
        return new AsyncResult<>("2");
    }

    @Override
    @Async("pool")
    public void async3() {
        System.out.println(1 / 0);
    }

    @Override
    @Async("pool")
    public Future<String> async4() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(1 / 0);
        return new AsyncResult<>("success");
    }
}
