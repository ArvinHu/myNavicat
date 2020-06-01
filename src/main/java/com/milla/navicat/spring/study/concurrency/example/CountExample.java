package com.milla.navicat.spring.study.concurrency.example;

import com.google.common.collect.Maps;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.milla.navicat.spring.study.concurrency.example
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2020/3/23 14:38
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/23 14:38
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class CountExample {
    static Map<String, Integer> data = Maps.newHashMap();

    public static void main(String[] args) throws InterruptedException {

        int concurrency = 100;
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(concurrency, 30, 2000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));
        ExecutorService executor = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(concurrency);
        CountDownLatch countDownLatch = new CountDownLatch(concurrency);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(concurrency);
        for (int i = 1; i <= concurrency; i++) {
            int finalI = i;
            executor.execute(() -> {
//                    cyclicBarrier.await();
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                add(finalI);
                semaphore.release();
                countDownLatch.countDown();
            });
        }
        executor.shutdown();
        countDownLatch.await();
        System.out.println("data: " + data.size());
    }


    static Lock lock = new ReentrantLock();

    private static void add(int index) {
        lock.lock();
        System.out.println(index);
        data.put("" + index, index);
        lock.unlock();
    }
}
