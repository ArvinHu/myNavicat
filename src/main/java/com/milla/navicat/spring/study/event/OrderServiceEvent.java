package com.milla.navicat.spring.study.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Package: com.milla.navicat.spring.study.event
 * @Description: <卖出事件[观察者模式，可扩展业务：推送邮件、推送订单完成信息、推送物流信息、推送获取优惠券信息]>
 * @Author: MILLA
 * @CreateDate: 2019/11/1 19:04
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/11/1 19:04
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class OrderServiceEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public OrderServiceEvent(Object source) {
        super(source);
    }
}
