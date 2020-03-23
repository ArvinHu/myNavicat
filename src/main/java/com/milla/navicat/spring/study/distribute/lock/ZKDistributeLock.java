package com.milla.navicat.spring.study.distribute.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Package: com.milla.navicat.spring.study.distribute.lock
 * @Description: <zookeeper分布式锁>
 * @Author: MILLA
 * @CreateDate: 2020/3/19 15:01
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/19 15:01
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class ZKDistributeLock implements Lock {
    private String lockPath;

    private ZkClient client;

    public ZKDistributeLock(String lockPath) {
        super();
        this.lockPath = lockPath;
        this.client = new ZkClient("hadoop-master:2181,hadoop-slave01:2181,hadoop-slave02:2181");
        this.client.setZkSerializer(new SerializableSerializer());
        int i = this.lockPath.substring(1).indexOf("/");
        String rootPath = this.lockPath.substring(0, i + 1);
        if (!this.client.exists(rootPath)) {
            this.client.createPersistent(rootPath);
        }
    }

    @Override
    public void lock() {
        if (!tryLock()) {
            waitForLock();
            lock();
        }

    }

    //等待获取锁
    private void waitForLock() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("删除了节点....." + dataPath);
                countDownLatch.countDown();
            }
        };
        //注册订阅
        client.subscribeDataChanges(lockPath, listener);
        //如果要创建的节点已经存在了就等待
        if (this.client.exists(lockPath)) {
            try {
                //阻塞
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //取消注册
        client.unsubscribeDataChanges(lockPath, listener);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        try {
            //创建临时节点
            this.client.createEphemeral(lockPath);
        } catch (RuntimeException e) {
//            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        this.client.delete(lockPath);

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
