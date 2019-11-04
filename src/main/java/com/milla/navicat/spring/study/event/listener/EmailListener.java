package com.milla.navicat.spring.study.event.listener;

import com.milla.navicat.spring.study.event.OrderServiceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Package: com.milla.navicat.spring.study.event.listener
 * @Description: <发送邮件业务>
 * @Author: MILLA
 * @CreateDate: 2019/11/1 19:07
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/11/1 19:07
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Component
@Slf4j
public class EmailListener implements ApplicationListener<OrderServiceEvent> {
    @Override
    public void onApplicationEvent(OrderServiceEvent event) {
        Object source = event.getSource();
        log.info("邮件发送服务,参数：{}", source);
        log.info("Email...1.根据帐号获取用户邮箱信息");
        log.info("Email...2.发送邮件");

    }
}
