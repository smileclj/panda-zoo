package com.panda.zoo.dubbo.provider.global;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author huixiangdou
 * @date 2018/7/3
 */
public class ThreadPoolTaskThrowExceptionExecutor extends ThreadPoolTaskExecutor {
    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = createThread(runnable);
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (e != null) {
                    throw new RuntimeException(e);
                }
            }
        });
        return thread;
    }
}
