package com.panda.zoo.common.test.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huixiangdou
 * @date 2018/7/23
 */
public class ThreadReentrantLockTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Runnable r = () -> {
            lock.lock();
            String name = Thread.currentThread().getName();
            System.out.println(name + "开始");
            try {
                Thread.sleep(1000 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "结束");
            lock.unlock();
        };
        Thread t1 = new Thread(null, r, "t1");
        Thread t2 = new Thread(null, r, "t2");
        t1.start();
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
