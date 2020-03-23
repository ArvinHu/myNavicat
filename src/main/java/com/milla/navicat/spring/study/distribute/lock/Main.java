package com.milla.navicat.spring.study.distribute.lock;

import com.google.common.collect.Sets;
import com.milla.navicat.spring.study.distribute.lock.service.IOrderService;
import com.milla.navicat.spring.study.distribute.lock.service.impl.*;

import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Package: com.milla.navicat.spring.study.distribute.lock
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2020/3/13 15:32
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/13 15:32
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        testThreeServiceWithThreadLocalLock();
//        testManyServiceWithThreadLocalLock();
//        testThreeServiceWithDistributeLock();
//        testManyServiceWithDistributeLock();
//        testThreeServiceWithImproveLock();
//        testThreeServiceWithReentrantLock();
//        testWithReentrantLock();
//        testNoLock();
    }

    //解决惊群效应，订单编号唯一
    private static void testThreeServiceWithThreadLocalLock() throws InterruptedException {
        //并发数量
        int currency = 50;
        //循环栅栏
        CyclicBarrier barrier = new CyclicBarrier(currency);

        IOrderService orderService1 = new OrderServiceDistributeThreadLocalLockImpl();
        IOrderService orderService2 = new OrderServiceDistributeThreadLocalLockImpl();
        IOrderService orderService3 = new OrderServiceDistributeThreadLocalLockImpl();

        highConcurrencyWithThreeService(currency, barrier, orderService1, orderService2, orderService3);
    }

    //解决惊群效应-订单唯一
    private static void testManyServiceWithThreadLocalLock() throws InterruptedException {
        //并发数量
        int currency = 50;
        //循环栅栏
        CyclicBarrier barrier = new CyclicBarrier(currency);

        Set<String> sets = Sets.newHashSet();
        for (int i = 0; i < currency; i++) {
            Thread task = new Thread(() -> {
                //使用以下两个实例订单均唯一，也都没有惊群效应
                IOrderService service = new OrderServiceDistributeThreadLocalLockImpl();
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                String code = service.createOrderCode();
                sets.add(code);
                System.out.println("订单编号： " + code);
            });
            task.setName("线程" + i);
            task.start();
        }
    }

    //解决惊群效应-订单唯一
    private static void testManyServiceWithDistributeImproveLock() throws InterruptedException {
        //并发数量
        int currency = 50;
        //循环栅栏
        CyclicBarrier barrier = new CyclicBarrier(currency);

        Set<String> sets = Sets.newHashSet();
        for (int i = 0; i < currency; i++) {
            Thread task = new Thread(() -> {
                //使用以下多实例订单均唯一，也都没有惊群效应
                IOrderService service = new OrderServiceDistributeImproveLockImpl();
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                String code = service.createOrderCode();
                sets.add(code);
                System.out.println("订单编号： " + code);
            });
            task.setName("线程" + i);
            task.start();
        }
    }


    //解决惊群效应，但是在服务是共享的情况下订单编号不唯一[每一个线程一个实例的情况下订单也唯一]
    private static void testThreeServiceWithImproveLock() throws InterruptedException {
        //并发数量
        int currency = 50;
        //循环栅栏
        CyclicBarrier barrier = new CyclicBarrier(currency);

        IOrderService orderService1 = new OrderServiceDistributeImproveLockImpl();
        IOrderService orderService2 = new OrderServiceDistributeImproveLockImpl();
        IOrderService orderService3 = new OrderServiceDistributeImproveLockImpl();

        highConcurrencyWithThreeService(currency, barrier, orderService1, orderService2, orderService3);
    }


    //使用分布式锁-订单唯一，但是具有惊群效应的后遗症
    private static void testThreeServiceWithDistributeLock() throws InterruptedException {
        //并发数量
        int currency = 50;
        //循环栅栏
        CyclicBarrier barrier = new CyclicBarrier(currency);

        IOrderService orderService1 = new OrderServiceDistributeLockImpl();
        IOrderService orderService2 = new OrderServiceDistributeLockImpl();
        IOrderService orderService3 = new OrderServiceDistributeLockImpl();

        highConcurrencyWithThreeService(currency, barrier, orderService1, orderService2, orderService3);
    }

    //使用分布式锁-订单唯一，但是具有惊群效应的后遗症
    private static void testManyServiceWithDistributeLock() throws InterruptedException {
        //并发数量
        int currency = 50;
        //循环栅栏
        CyclicBarrier barrier = new CyclicBarrier(currency);

        Set<String> sets = Sets.newHashSet();
        for (int i = 0; i < currency; i++) {
            Thread task = new Thread(() -> {
                //使用以下两个实例订单均唯一，也都没有惊群效应
                IOrderService service = new OrderServiceDistributeLockImpl();
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                String code = service.createOrderCode();
                System.out.println("订单编号： " + code);
            });
            task.setName("线程" + i);
            task.start();
        }
    }

    //使用可重入锁-分布式下不唯一
    private static void testWithReentrantLock() {
        //并发数量
        int currency = 20;
        //循环栅栏
        CyclicBarrier barrier = new CyclicBarrier(currency);

        IOrderService orderService = new OrderServiceReentrantLockImpl();

        simulationHighConcurrency(currency, barrier, orderService);
    }

    //使用可重入锁-分布式下不唯一
    private static void testThreeServiceWithReentrantLock() throws InterruptedException {
        //并发数量
        int currency = 50;
        //循环栅栏
        CyclicBarrier barrier = new CyclicBarrier(currency);

        IOrderService orderService1 = new OrderServiceReentrantLockImpl();
        IOrderService orderService2 = new OrderServiceReentrantLockImpl();
        IOrderService orderService3 = new OrderServiceReentrantLockImpl();

        highConcurrencyWithThreeService(currency, barrier, orderService1, orderService2, orderService3);
    }

    private static void highConcurrencyWithThreeService(int currency, CyclicBarrier barrier, IOrderService orderService1, IOrderService orderService2, IOrderService orderService3) {
        for (int i = 0; i < currency; i++) {
            int finalI = i;
            Thread task = new Thread(() -> {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                if (finalI % 2 == 0) {
                    String code = orderService1.createOrderCode();
                    System.out.println("服务一： " + code);
                } else if (finalI % 3 == 0) {
                    String code = orderService2.createOrderCode();
                    System.out.println("服务二： " + code);

                } else {
                    String code = orderService3.createOrderCode();
                    System.out.println("服务三： " + code);
                }
            });
            task.setName("线程" + i);
            task.start();
        }
    }

    //不添加锁-不唯一
    private static void testNoLock() {
        //并发数量
        int currency = 20;
        //循环栅栏
        CyclicBarrier barrier = new CyclicBarrier(currency);

        IOrderService orderService = new OrderServiceImpl();

        simulationHighConcurrency(currency, barrier, orderService);
    }

    //模拟高并发
    private static void simulationHighConcurrency(int currency, CyclicBarrier barrier, IOrderService orderService) {
        for (int i = 0; i < currency; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 已经初始化好了，等待执行");
                try {
                    //等待所有的线程都就位并发执行
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                String code = orderService.createOrderCode();
                System.out.println(code);
            }).start();
        }
    }
}
