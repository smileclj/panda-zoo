package com.panda.zoo.common.test.thread;

/**
 * Created by huixiangdou on 2017/7/23.
 */
public class ThreadTwoStart {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            System.out.println("启动了");
        });
        t.start();
        t.start();
    }
}
