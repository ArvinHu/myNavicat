package com.milla.navicat.spring.study.service;

/**
 * @Package: com.milla.navicat.spring.study.service
 * @Description: <根据不同的折扣计算不同的金额>
 * @Author: MILLA
 * @CreateDate: 2019/11/1 16:58
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/11/1 16:58
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface CalculateAmountService {
    //根据用户的类型打折
    double saleByDisCount(double fee);
}
