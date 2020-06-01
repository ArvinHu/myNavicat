package com.milla.navicat.spring.study.concurrency.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ListBlockLockQueue {
    //队列长度
    private int size = 5;
    //队列容器

    //JDK中阻塞队列的实现方式.采用可重入锁和信号量进行控制

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[size];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ListBlockLockQueue queue = new ListBlockLockQueue();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    queue.put("" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "一");
        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + ",获取数据： " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "二");
        t2.start();
        t1.start();
        Thread.sleep(1000);
        queue.put("123456");
        Thread.sleep(1000);
        queue.put("00000");
        Thread.sleep(1000);
        queue.put("555555");
    }

}
