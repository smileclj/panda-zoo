package com.panda.zoo.common.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huixiangdou
 * @date 2019/2/11
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("Panda-Thread-Pool" + count.getAndAdd(1));
                return t;
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        });

    }
}
