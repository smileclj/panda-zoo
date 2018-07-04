package com.panda.zoo.common.test.java.thread;


import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * @author huixiangdou
 * @date 2018/7/3
 */
public class GuavaThread {
    @Test
    public void ListeningExecutorService() {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    }

}
