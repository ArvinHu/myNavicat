package com.milla.navicat.spring.study.concurrency.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.milla.navicat.spring.study.concurrency.example
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2020/3/26 18:00
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/26 18:00
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class ListBlockQueue {
    //队列长度
    private int size = 10;
    //队列容器
    private List<String> list = new ArrayList<>();
    //加锁
    private String push = "add";
    //减锁
    private String pull = "pull";
    //当前数据量
    private AtomicInteger integer = new AtomicInteger();


    public void add(String element) {
        synchronized (push) {

            while (integer.get() == size) {
//                System.out.println(Thread.currentThread().getName() + " waiting .... ");
                try {
                    push.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (list.add(element)) {
                System.out.println(Thread.currentThread().getName() + " 线程进入...:" + element);
                integer.incrementAndGet();
                if (list.size() > 0) {
                    synchronized (pull) {
                        pull.notifyAll();
                        pull();
                    }
                }
            }
        }
    }

    public void pull() {
        synchronized (push) {
            String remove = list.remove(0);
            System.out.println(Thread.currentThread().getName() + " 执行删除..." + remove);
            integer.decrementAndGet();
            push.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ListBlockQueue queue = new ListBlockQueue();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                queue.add(Thread.currentThread().getName() + " - " + i);
            }
        }, "①");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                queue.add(Thread.currentThread().getName() + " - " + i);
            }
        }, "②");
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                queue.add(Thread.currentThread().getName() + " - " + i);
            }
        }, "③");
        t1.start();
        t2.start();
        t3.start();
//        Thread.sleep(1000L);
//        queue.pull();
//        Thread.sleep(1000L);
//        queue.pull();
//        Thread.sleep(1000L);
//        queue.pull();
//        Thread.sleep(1000L);
//        queue.pull();

    }

}
