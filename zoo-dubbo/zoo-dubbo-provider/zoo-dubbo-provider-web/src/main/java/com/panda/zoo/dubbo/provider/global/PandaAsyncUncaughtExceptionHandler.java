package com.panda.zoo.dubbo.provider.global;

import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @author huixiangdou
 * @date 2018/7/4
 */
public class PandaAsyncUncaughtExceptionHandler extends SimpleAsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        System.out.println("void 异常捕捉");
    }
}
