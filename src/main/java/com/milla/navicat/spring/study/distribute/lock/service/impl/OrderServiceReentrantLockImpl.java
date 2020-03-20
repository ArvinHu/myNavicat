package com.milla.navicat.spring.study.distribute.lock.service.impl;

import com.milla.navicat.spring.study.distribute.lock.OrderCodeGenerator;
import com.milla.navicat.spring.study.distribute.lock.ZKDistributeLock;
import com.milla.navicat.spring.study.distribute.lock.service.IOrderService;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.milla.navicat.spring.study.distribute.lock.service.impl
 * @Description: <可重入锁>
 * @Author: MILLA
 * @CreateDate: 2020/3/13 14:57
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/13 14:57
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class OrderServiceReentrantLockImpl implements IOrderService {

    private static OrderCodeGenerator generator = new OrderCodeGenerator();


    private Lock lock = new ReentrantLock();

    @Override
    public String createOrderCode() {
        try {
            lock.lock();
            return generator.getOrderCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

}
