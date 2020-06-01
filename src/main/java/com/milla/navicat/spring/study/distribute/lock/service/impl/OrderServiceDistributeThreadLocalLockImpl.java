package com.milla.navicat.spring.study.distribute.lock.service.impl;

import com.milla.navicat.spring.study.distribute.lock.OrderCodeGenerator;
import com.milla.navicat.spring.study.distribute.lock.ZKDistributeThreadLocalLock;
import com.milla.navicat.spring.study.distribute.lock.service.IOrderService;

import java.util.concurrent.locks.Lock;

/**
 * @Package: com.milla.navicat.spring.study.distribute.lock.service.impl
 * @Description: <使用ThreadLocal解决订单紊乱问题>
 * @Author: MILLA
 * @CreateDate: 2020/3/13 14:57
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/13 14:57
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class OrderServiceDistributeThreadLocalLockImpl implements IOrderService {
    private static OrderCodeGenerator generator = new OrderCodeGenerator();


    private Lock lock = new ZKDistributeThreadLocalLock("/demo");

    @Override
    public String createOrderCode() {
        try {
            lock.lock();
            System.out.println(generator);

            return generator.getOrderCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

}
