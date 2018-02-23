package com.panda.zoo.common.test.java.lambda;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author huixiangdou
 * @date 2018/2/3
 */
public class LambdaTest {

    /**
     * 匿名内部类
     */
    @Test
    public void test1() throws Exception {
        //无参数
        Runnable r = () -> System.out.println("执行");
        Thread t1 = new Thread(r);
        t1.start();

        //无参数，代码块
        Thread t2 = new Thread(() -> {
            System.out.println("执行");
        });
        t2.start();

        //原始，无参数
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行");
            }
        });
        t3.start();

        //原始，带参数
        Button btn = new Button();
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("执行");
            }
        });

        btn.addActionListener(e -> System.out.println("执行"));
        btn.addActionListener((ActionEvent e) -> System.out.println("执行"));
    }

    /**
     * stream api
     */
    @Test
    public void test2() {
        List<Integer> list = Lists.newArrayList(1,2,3);
    }
}
