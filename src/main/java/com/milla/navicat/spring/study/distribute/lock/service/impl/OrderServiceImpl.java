package com.milla.navicat.spring.study.distribute.lock.service.impl;

import com.milla.navicat.spring.study.distribute.lock.OrderCodeGenerator;
import com.milla.navicat.spring.study.distribute.lock.service.IOrderService;

/**
 * @Package: com.milla.navicat.spring.study.distribute.lock.service.impl
 * @Description: <不加任何锁>
 * @Author: MILLA
 * @CreateDate: 2020/3/13 14:57
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/13 14:57
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class OrderServiceImpl implements IOrderService {

    private static OrderCodeGenerator generator = new OrderCodeGenerator();

    @Override
    public String createOrderCode() {
        return generator.getOrderCode();
    }

}
