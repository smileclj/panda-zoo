package com.panda.zoo.common.test.thread;

/**
 * Created by huixiangdou on 2017/5/3.
 */
public class ThreadExceptionTest {

    public static void main(String[] args) {

        try {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        if (i == 10) {
                            throw new RuntimeException();
                        }
                        System.out.println(i);
                    }
                }
            };
            Thread t = new Thread(r);
            t.start();
        } catch (Exception e) {
            System.out.println("异常");
            e.printStackTrace();
        }
    }
}
