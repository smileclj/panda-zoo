package com.panda.zoo.common.test.example.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock源码分析案例
 *
 * @author huixiangdou
 * @date 2019/2/12
 */
public class ReentrantLockTest {
    private static final long ONE_HOUR = 60 * 60 * 1000;
    private static int nThreads = 3;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        ExecutorService pool = Executors.newFixedThreadPool(nThreads, new ThreadFactory() {
            private AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "[ReentrantLockTestPool-Thread-" + count.addAndGet(1) + "]");
            }
        });

        Runnable r = () -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "执行了");
                Thread.sleep(ONE_HOUR);
            } catch (InterruptedException e) {
                //ignore
            } finally {
                lock.unlock();
            }
        };

        for (int i = 0; i < nThreads; i++) {
            pool.execute(r);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
