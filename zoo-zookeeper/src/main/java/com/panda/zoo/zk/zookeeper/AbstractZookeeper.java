package com.panda.zoo.zk.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public abstract class AbstractZookeeper implements Watcher {
    private ZooKeeper zooKeeper;
    private String host; //host:port,host:port
    private int sessionTimeOut;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    protected abstract void init();

    public void connect(String host) throws IOException,
            InterruptedException {
        //异步创建会话
        zooKeeper = new ZooKeeper(host, sessionTimeOut, this);
        countDownLatch.await();
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }

    public String createNode(String path, byte[] data, List<ACL> acl, CreateMode createMode) throws KeeperException,
            InterruptedException {
        return zooKeeper.create(path, data, acl, createMode);
    }

    public String createNode(String path, byte[] data, CreateMode createMode) throws KeeperException,
            InterruptedException {
        return zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
    }

    public String createNode(String path, String data, List<ACL> acl, CreateMode createMode) throws KeeperException,
            InterruptedException {
        return zooKeeper.create(path, data.getBytes(), acl, createMode);
    }

    public String createNode(String path, String data, CreateMode createMode) throws KeeperException,
            InterruptedException {
        return zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
    }

    public Stat existsNode(String path, boolean watch) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path, watch);
    }

    /**
     * 根据路径查询节点是否存在（默认监控节点）
     *
     * @param path 路径
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public Stat existsNode(String path) throws KeeperException, InterruptedException {
        return this.zooKeeper.exists(path, true);
    }

    /**
     * 根据路径查询节点是否存在（设置一个监控）
     *
     * @param path 路径
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public Stat existsNode(String path, Watcher watcher) throws KeeperException, InterruptedException {
        return this.zooKeeper.exists(path, watcher);
    }

    /**
     * 根据路径获取所有孩子节点
     *
     * @param path 路径
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public List<String> getChildren(String path) throws KeeperException,
            InterruptedException {
        return this.zooKeeper.getChildren(path, false);
    }

    /**
     * 根据路径设置节点数据
     *
     * @param path    路径
     * @param data    数据
     * @param version 版本
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public Stat setData(String path, byte[] data, int version) throws KeeperException,
            InterruptedException {
        return this.zooKeeper.setData(path, data, version);
    }

    /**
     * 根据路径获取节点数据
     *
     * @param path 路径
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public byte[] getData(String path) throws KeeperException,
            InterruptedException {
        return this.zooKeeper.getData(path, false, null);
    }

    /**
     * 根据路径删除节点
     *
     * @param path    路径
     * @param version 版本
     * @throws InterruptedException
     * @throws KeeperException
     */
    public void deleteNode(String path, int version) throws InterruptedException,
            KeeperException {
        this.zooKeeper.delete(path, version);
    }

    /**
     * 关闭zookeeper连接
     *
     * @throws InterruptedException
     */
    public void close() throws InterruptedException {
        if (zooKeeper != null) {
            try {
                zooKeeper.close();
                zooKeeper = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getSessionTimeOut() {
        return sessionTimeOut;
    }

    public void setSessionTimeOut(int sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }
}