package com.panda.zoo.common.test.thread;

/**
 * Created by huixiangdou on 2017/5/3.
 */
public class ThreadGroupTest {
    public static void main(String[] args) {
        ThreadGroup tg = Thread.currentThread().getThreadGroup();
        System.out.println(tg.getName());
        System.out.println(tg.getMaxPriority());

        Thread t = new Thread();
        System.out.println(t.getThreadGroup().getName());
    }
}
