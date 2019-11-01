package com.milla.navicat.spring.study.service.impl;

import com.milla.navicat.spring.study.service.CalculateAmountService;
import org.springframework.stereotype.Service;

/**
 * @Package: com.milla.navicat.spring.study.service.impl
 * @Description: <至尊会员折扣>
 * @Author: MILLA
 * @CreateDate: 2019/11/1 16:59
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/11/1 16:59
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Service(value = "gold")
public class GoldCalculateAmountServiceImpl implements CalculateAmountService {

    @Override
    public double saleByDisCount(double fee) {
        return fee * 0.55;
    }
}
