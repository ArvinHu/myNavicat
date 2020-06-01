package com.milla.navicat.spring.study.concurrency.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.milla.navicat.spring.study.concurrency.example
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2020/3/27 15:05
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/27 15:05
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class ProducerAndConsumerTest {
    public static void main(String[] args) {
        ProducerAndConsumerTest service = new ProducerAndConsumerTest();
        for (int i = 0; i < 10; i++) {
            new Thread(new Producer("p-1", service), "A").start();
            new Thread(new Producer("p-1", service), "B").start();
        }
//        new Thread(new Producer("p-2", service), "B").start();
        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer(service), "c-1").start();
        }
//        new Thread(new Producer("p-2", service), "B").start();
//        new Thread(new Producer("p-3", service), "C").start();
//        new Thread(new Producer("p-4", service), "D").start();
//        new Thread(new Producer("p-5", service), "E").start();


    }

    //产品总量
    private int size = 5;
    //产品容器

    final List<String> items = new ArrayList(size);
    //锁
    final Lock lock = new ReentrantLock();
    //生产
    Condition produce = lock.newCondition();
    //消费
    Condition consume = lock.newCondition();


    //生产
    void produce(String product) throws InterruptedException {
        try {
            lock.lock();
            //加锁
            //如果产品容器已经满了的话就阻塞
            while (items.size() >= size) {
                System.out.println(Thread.currentThread().getName() + " - 生产者阻塞");
                produce.await();
            }
            //加入数据
            String element = Thread.currentThread().getName() + " - " + product;
            items.add(element);
            System.out.println(Thread.currentThread().getName() + " - 新增加的元素为：" + element);
            //解锁
            consume.signal();
        } finally {
            lock.unlock();

        }
    }

    //消费
    void consume() throws InterruptedException {
        try {
            lock.lock();
            //如果产品容器已经满了的话就阻塞
            while (items.size() <= 0) {
                System.out.println(Thread.currentThread().getName() + " - 消费者阻塞");
                consume.await();
            }
            //移除第一位的数据
            String remove = items.remove(0);
            System.out.println(Thread.currentThread().getName() + " - 移除的数据为：" + remove);
            produce.signal();
        } finally {
            lock.unlock();

        }
    }


}

//生产者
class Producer implements Runnable {
    private String product;
    private ProducerAndConsumerTest main;

    public Producer(String product, ProducerAndConsumerTest main) {
        this.product = product;
        this.main = main;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; ; i++) {
                main.produce(i + "");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//消费者
class Consumer implements Runnable {
    private ProducerAndConsumerTest main;

    public Consumer(ProducerAndConsumerTest main) {
        this.main = main;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; ; i++) {
                main.consume();
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
