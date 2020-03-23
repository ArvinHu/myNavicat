package com.milla.navicat.spring.study.distribute.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Package: com.milla.navicat.spring.study.distribute.lock
 * @Description: <zookeeper分布式锁[解决惊群效应]>
 * @Author: MILLA
 * @CreateDate: 2020/3/19 15:01
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/19 15:01
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class ZKDistributeThreadLocalLock implements Lock {

    private String lockPath;


    private ThreadLocal<String> beforePath = new ThreadLocal<>();

    private ThreadLocal<String> currentPath = new ThreadLocal<>();

    private ZkClient client;

    public ZKDistributeThreadLocalLock(String lockPath) {
        super();
        this.lockPath = lockPath;
        this.client = new ZkClient("hadoop-master:2181,hadoop-slave01:2181,hadoop-slave02:2181");
        this.client.setZkSerializer(new SerializableSerializer());
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
                System.out.println(Thread.currentThread().getName() + " ;//删除了节点....." + dataPath);
                countDownLatch.countDown();
            }

        };
        //注册订阅
        client.subscribeDataChanges(beforePath.get(), listener);
        //如果要创建的节点已经存在了就等待
        if (this.client.exists(beforePath.get())) {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //取消注册
        client.unsubscribeDataChanges(beforePath.get(), listener);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {

        //如果当前节点是空创建临时连续节点
        if (this.currentPath.get() == null) {
            this.currentPath.set(this.client.createEphemeralSequential(lockPath + "/", "aaa"));
        }
        //获取所有的子节点

        List<String> children = client.getChildren(lockPath);
        //排序
        Collections.sort(children);
        String currentNode = this.currentPath.get();
        //判断当前节点是否是最小的
        if (currentNode.equals(lockPath + "/" + children.get(0))) {
            return true;
        } else {
            int index = children.indexOf(currentNode.substring(lockPath.length() + 1));
            beforePath.set(lockPath + "/" + children.get(index - 1));
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        this.client.delete(currentPath.get());
        currentPath.remove();

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}