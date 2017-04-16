package com.panda.zoo.zk.test.curator;

import com.google.common.collect.Maps;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by huixiangdou on 2017/4/1.
 */
public class CuratorTest {
    private static final String zkHost = "192.168.1.103";
    private static final int zkPort1 = 2181;
    private static final int zkPort2 = 2182;
    private static final int zkPort3 = 2183;
    private static final String zkAddress = zkHost + ":" + zkPort1 + "," + zkHost + ":" + zkPort2 + "," + zkHost + ":" + zkPort3;
    private static final RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

    private static final CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
    private static final CuratorFramework client2 = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
    private static final CuratorFramework client3 = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);

    private static final Map<String, String> clientMap = Maps.newHashMap();
    private static final int DEFAULT_DURATION = 20;


    static {
        client.start();
        client2.start();
        client3.start();
        clientMap.put(client.toString(), "client1");
        clientMap.put(client2.toString(), "client2");
        clientMap.put(client3.toString(), "client3");
    }

    /**
     * 获取节点内容
     *
     * @throws Exception
     */
    @Test
    public void getData() throws Exception {
        String path = "/panda";
        System.out.println(new String(client.getData().forPath(path)));
    }

    /**
     * 分布式锁
     *
     * @throws Exception
     */
    @Test
    public void lock() throws Exception {
        String lockPath = "/panda";
        final CountDownLatch latch = new CountDownLatch(2);
        final InterProcessMutex lock = new InterProcessMutex(client, lockPath);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    if (lock.acquire(3, TimeUnit.SECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName());
                            Thread.sleep(2000);
                            latch.countDown();
                        } finally {
                            lock.release();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(2);
        ThreadFactory factory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        };

        pool.execute(new Thread(r));
        pool.execute(new Thread(r));
        latch.await();
    }

    /**
     * master选举
     */
    private static void leadElection() {
        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                // this callback will get called when you are the leader
                // do whatever leader work you need to and only exit
                // this method when you want to relinquish leadership
                while (true) {
                    System.out.println(clientMap.get(client.toString()));
                    sleep(3);
                }
            }

            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                super.stateChanged(client, newState);
            }
        };
        String leaderPath = "/panda/20170406";
        LeaderSelector selector1 = new LeaderSelector(client, leaderPath, listener);
        selector1.start();

        LeaderSelector selector2 = new LeaderSelector(client2, leaderPath, listener);
        selector2.start();

        LeaderSelector selector3 = new LeaderSelector(client3, leaderPath, listener);
        selector3.start();

//        sleep(10);
//        if (selector1.hasLeadership()) {
//            selector1.close();
//        }
//        if (selector2.hasLeadership()) {
//            selector2.close();
//        }
//        if (selector3.hasLeadership()) {
//            selector3.close();
//        }
    }

    private static void sleep(int duration) {
        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleep() {
        sleep(DEFAULT_DURATION);
    }

    public static void main(String[] args) {
        leadElection();
    }
}
