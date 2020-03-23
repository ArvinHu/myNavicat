package com.milla.navicat.spring.study.distribute.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Package: com.milla.navicat.spring.study.distribute.lock
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2020/3/23 14:28
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/23 14:28
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class RedisDistributedLock implements Lock {
    @Override
    public void lock() {


    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
