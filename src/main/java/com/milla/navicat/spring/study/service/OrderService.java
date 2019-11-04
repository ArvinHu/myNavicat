package com.milla.navicat.spring.study.service;

/**
 * @Package: com.milla.navicat.spring.study.service
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/11/1 16:31
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/11/1 16:31
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface OrderService {
    double getPaymentAmount(String userType, double fee);

    boolean payment(String userType, double amount, double discountAmount);
}
