package com.milla.navicat.spring.study.event.listener;

import com.milla.navicat.spring.study.event.OrderServiceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Package: com.milla.navicat.spring.study.event.listener
 * @Description: <优惠券业务类>
 * @Author: MILLA
 * @CreateDate: 2019/11/4 16:04
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/11/4 16:04
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Slf4j
@Component
public class DiscountCouponListener implements ApplicationListener<OrderServiceEvent> {
    @Override
    public void onApplicationEvent(OrderServiceEvent event) {
        Object source = event.getSource();
        log.info("优惠券业务,参数：{}", source);
        log.info("优惠券...1.根据帐号获取用户信息");
        log.info("优惠券...2.分发优惠券");
        log.info("优惠券...3.其他优惠券业务");

    }
}
